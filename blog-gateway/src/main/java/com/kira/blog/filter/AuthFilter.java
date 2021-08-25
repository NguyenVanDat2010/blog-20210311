package com.kira.blog.filter;

import com.alibaba.fastjson.JSONObject;
import com.kira.blog.config.AuthFilterProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.kira.blog.config.JwtProperty;
import com.kira.blog.domain.JwtPayload;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.kira.blog.utils.JwtUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * This filter purpose is:
 * filter the uri which need token
 */
@Component
@EnableConfigurationProperties({AuthFilterProperty.class, JwtProperty.class})
public class AuthFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Resource
    private AuthFilterProperty authFilterProperty;

    @Resource
    private JwtProperty jwtProperty;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //String method = ctx.getRequest().getMethod();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        return (!isAllowPath(requestURI));
    }

    private boolean isAllowPath(String requestURI) {
        boolean flag = false;
        for (String path : this.authFilterProperty.getAllowPaths()) {
            if (requestURI.startsWith(path)) {
                flag = true;
                break;
            }
        }
        logger.info("request uri is: {}, need auth filter result is: {}", requestURI, Boolean.valueOf(flag));
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        JwtPayload infoFromToken;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String authorization = request.getHeader("Authorization");
        logger.info("authorization is: {}", authorization);
        if (StringUtils.isBlank(authorization)) {
            logger.warn("Authorization is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            return null;
        }
        String tokenJson = (String) this.stringRedisTemplate.opsForValue().get(authorization);
        if (StringUtils.isBlank(tokenJson)) {
            logger.warn("authorization token expired");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(tokenJson);
        String accessToken = jsonObject.get("accessToken").toString();
        String refreshToken = jsonObject.get("refreshToken").toString();
        try {
            infoFromToken = JwtUtils.getInfoFromToken(accessToken, this.jwtProperty.getRsaPubKey());
            logger.info("token parse result is: {}", infoFromToken);
        } catch (ExpiredJwtException e) {
            logger.info("access token expire");
            try {
                logger.info("start to parse refresh token");
                infoFromToken = JwtUtils.getInfoFromToken(refreshToken, this.jwtProperty.getRsaPubKey());
                String newAccessToken = JwtUtils.generateToken(infoFromToken, this.jwtProperty.getRsaPriKey(), this.jwtProperty.getExpire().longValue());
                String newRefreshToken = JwtUtils.generateToken(infoFromToken, this.jwtProperty.getRsaPriKey(), this.jwtProperty.getRefreshExpire().longValue());
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("accessToken", newAccessToken);
                tokenMap.put("refreshToken", newRefreshToken);
                String newTokenJson = JSONObject.toJSONString(tokenMap);
                logger.info("redis key is: {}, tokenJson is: {}", authorization, newTokenJson);
                //Moi lan su dung token, se tu dong reset lai thoi gian session login trong redis
                this.stringRedisTemplate.opsForValue().set(authorization, newTokenJson, this.jwtProperty.getRefreshExpire().longValue(), TimeUnit.MINUTES);
                this.stringRedisTemplate.opsForValue().set(infoFromToken.getUsername(), authorization, this.jwtProperty.getRefreshExpire().longValue(), TimeUnit.MINUTES);
            } catch (Exception e1) {
                logger.info("fail to parse refresh token for reason: {}", e1);
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(403);
                return null;
            }
        } catch (Exception e1) {
            logger.info("fail to parse token for reason: {}", e1);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            return null;
        }
        //Set header cho request ben admin va app voi headers la username, roleRight, roleStatus
        //ctx.addZuulRequestHeader("deviceId", infoFromToken.getDeviceId());
        //ctx.addZuulRequestHeader("userUuid", infoFromToken.getUserUuid());
        ctx.addZuulRequestHeader("username", infoFromToken.getUsername());
        ctx.addZuulRequestHeader("roleRight", infoFromToken.getRoleRight());
        ctx.addZuulRequestHeader("roleStatus", infoFromToken.getRoleStatus());
        return null;
    }
}
