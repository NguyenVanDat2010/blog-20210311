package com.kira.blog.filter;

import com.alibaba.fastjson.JSONObject;
import com.kira.blog.ciphers.AesUtils;
import com.kira.blog.ciphers.RsaUtils;
import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.constant.RequestConst;
import com.kira.blog.exception.BizException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * This filter purpose is:
 * encrypt the com.kira.common.response body
 */
@Component
public class ResponseEncryptFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ResponseEncryptFilter.class);

    @Value("${blog.encryption.enable}")
    private boolean apiEncrypt;

    @Value("${blog.jwt.rsaPriKey}")
    private String priKey;

    private final static String dcUrl = "/app/dc/customers";

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return -3;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestURI = request.getRequestURI();
        String header = request.getHeader("X-Client-Id");
        return apiEncrypt && !requestURI.startsWith(dcUrl);
    }

    @Override
    public Object run() throws ZuulException {
        // 1. base on RequestContext to get request and com.kira.common.response
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
        String body = null;
        if (zuulResponse != null) {
            RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
            try {
                body = IOUtils.toString(resp.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 2. get request AES key
        String aesKey = request.getHeader(RequestConst.ENCODE_KEY);
        String decryptAesKey;
        try {
            decryptAesKey = RsaUtils.decryptByPrivateKey(aesKey, priKey);
        } catch (Exception e) {
            logger.error("fail to decrypt aesKey by RsaUtils for reason: {}", e);
            return Mono.error(new BizException(ExceptionEnum.AES_KEY_DECRYPT_ERROR));
        }

        // 3. base on com.kira.common.response status to operate
        String status = ctx.getResponse().getStatus() + "";
        if (!status.startsWith("2")) {
            return null;
        }
        // logger.info("get the responseBody is: {}", body);
        JSONObject bodyJson = JSONObject.parseObject(body);
        Object data = bodyJson.get("data");
        try {
            String encryptBody = AesUtils.encryptByECB(decryptAesKey, JSONObject.toJSONString(data));
            bodyJson.put("data", encryptBody);
            // logger.info("after encrypt, com.kira.common.response data is: {}", bodyJson.toJSONString());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        ctx.setResponseBody(bodyJson.toJSONString());

        return null;
    }
}
