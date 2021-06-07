package com.kira.blog.filter;

import com.alibaba.fastjson.JSONObject;
import com.kira.blog.ciphers.AesUtils;
import com.kira.blog.ciphers.RsaUtils;
import com.kira.blog.constant.RequestConst;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * This filter purpose is:
 * decrypt the request body
 */
@Component
public class RequestDecryptFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(RequestDecryptFilter.class);

    @Value("${blog.encryption.enable}")
    private boolean apiEncrypt;

    @Value("${blog.jwt.rsaPriKey}")
    private String priKey;

    private final static String dcUrl = "/app/dc/customers";

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
        String method = ctx.getRequest().getMethod();
        String requestURI = ctx.getRequest().getRequestURI();
        return (this.apiEncrypt && !"GET".equalsIgnoreCase(method) && !requestURI.startsWith(dcUrl));
    }

    @Override
    public Object run() throws ZuulException {
        String decryptAesKey, requestBody;
        logger.info("start to decrypt the request body.");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String aesKey = request.getHeader("Encode-Key");
        if (StringUtils.isBlank(aesKey)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            byte[] bytes = "{\"code\":\"-1\",\"msg\":\"ENCODE KEY IS EMPTY\"}".getBytes(StandardCharsets.UTF_8);
            ctx.setResponseBody(new String(bytes));
            return null;
        }
        String body = null;
        try {
            ServletInputStream servletInputStream = request.getInputStream();
            body = StreamUtils.copyToString((InputStream)servletInputStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("fail to get request body inputStream");
        }
        if (StringUtils.isBlank(body)) {
            body = "{}";
        }
        JSONObject jsonObject = JSONObject.parseObject(body);
        String requestData = (String)jsonObject.get("reqData");
        if (StringUtils.isBlank(requestData)) {
            return null;
        }
        try {
            decryptAesKey = RsaUtils.decryptByPrivateKey(aesKey, this.priKey);
            logger.info("aes key is: {}", decryptAesKey);
        } catch (Exception e) {
            logger.error("fail to decrypt aesKey by RsaUtils for reason: " + e);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            return null;
        }
        try {
            requestBody = AesUtils.decryptByECB(decryptAesKey, requestData);
        } catch (GeneralSecurityException e) {
            logger.error("fail to decrypt request body by AesKey for reason: " + e);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            return null;
        }
        byte[] bodyBytes = requestBody.getBytes();
        ctx.setRequest(new HttpServletRequestWrapper(request) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(bodyBytes);
            }

            @Override
            public int getContentLength() {
                return bodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bodyBytes.length;
            }
        });
        return null;
    }
}