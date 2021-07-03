package com.kira.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kira.blog.config.JwtProperty;
import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.constant.RoleConst;
import com.kira.blog.domain.JwtPayload;
import com.kira.blog.exception.BizException;
import com.kira.blog.mapper.LoginMapper;
import com.kira.blog.mapper.UserMapper;
import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.dto.SignUpDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.pojo.vo.SignUpVO;
import com.kira.blog.service.LoginService;
import com.kira.blog.service.RoleService;
import com.kira.blog.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    private static final String forceLoginFlag = "true";

    @Resource
    private JwtProperty jwtProperty;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public SignUpVO signUp(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        logger.info("User: {} login", loginDTO.getUsername());
        String username = loginDTO.getUsername();

        UserPO userPO = userMapper.getUserByUsername(username);
        if (null == userPO || "1".equals(userPO.getIsDelete())) {
            throw new BizException(ExceptionEnum.USER_NOT_EXIST);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginDTO.getPassword(), userPO.getPassword())) {
            throw new BizException(ExceptionEnum.USER_WRONG_PASSWORD);
        }

        if (!forceLoginFlag.equals(loginDTO.getForceLogin())) {
            String authInRedis = stringRedisTemplate.opsForValue().get(username);
            if (!StringUtils.isEmpty(authInRedis)) {
                throw new BizException(ExceptionEnum.USER_HAD_LOGIN);
            }
        } else {
            String authInRedis = stringRedisTemplate.opsForValue().get(username);
            if (!StringUtils.isEmpty(authInRedis)) {
                stringRedisTemplate.delete(authInRedis);
            }
        }
        LoginVO loginVO = loginMapper.getUserByUsername(username);
        JwtPayload jwtPayload = new JwtPayload();
        BeanUtils.copyProperties(loginVO, jwtPayload);

        String accessToken;
        String refreshToken;
        try {
            accessToken = JwtUtils.generateToken(jwtPayload, jwtProperty.getRsaPriKey(), jwtProperty.getExpire());
            refreshToken = JwtUtils.generateToken(jwtPayload, jwtProperty.getRsaPriKey(), jwtProperty.getRefreshExpire());
        } catch (Exception e) {
            logger.error("fail to generate token, cause: {}", e.getMessage());
            throw new BizException(ExceptionEnum.TOKEN_GENERATED_ERROR);
        }

        String sessionId = UUID.randomUUID().toString();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);
        stringRedisTemplate.opsForValue().set(sessionId, JSONObject.toJSONString(tokenMap), jwtProperty.getRefreshExpire(), TimeUnit.MINUTES);
        loginVO.setAccessToken(sessionId);
        stringRedisTemplate.opsForValue().set(username, sessionId, jwtProperty.getRefreshExpire(), TimeUnit.MINUTES);

        return loginVO;
    }

    @Override
    public void logout(String username, String remoteAddress) {
        String sessionId = stringRedisTemplate.opsForValue().get(username);
        if (!StringUtils.isEmpty(sessionId)) {
            stringRedisTemplate.delete(sessionId);
        }
        stringRedisTemplate.delete(username);
    }
}
