package com.kira.blog.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* RestTemplate Utils
* */
public class RestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RestUtil.class);

    private static final String CHARSET_NAME = "UTF-8";

    private static final String MEDIA_TYPE = "application/json; charset=UTF-8";

    /*
    * Send POST request to defined url
    * */
    public static String post(String url, Object body, Map<String, String> headers) throws Exception {
        if (ObjectUtils.isEmpty(body)) {
            body = new HashMap<>();
        }

        // parse request body to json
        String bodyJson = JSON.toJSONString(body);
        RestTemplate restTemplate = getRestTemplate();

        // set request headers
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MEDIA_TYPE);
        httpHeaders.setContentType(type);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(httpHeaders::set);
        }

        HttpEntity<String> entity = new HttpEntity<>(bodyJson, httpHeaders);
        logger.info("RestTemplate com.kira.common.utils start, url is: {}", url);
        try {
            return restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            logger.error("send RestTemplate request error: {}", e);
            throw new Exception(e);
        }

    }

    /*
    * Config the RestTemplate to support both http and https request
    * */
    private static RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // trust all strategy
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", csf)
                .build();

        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        // aim to deal with the https com.kira.common.response is not GZIP.
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept-Encoding", "*"));
        headers.add(new BasicHeader("Accept", "*/*"));

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .setDefaultHeaders(headers)
                .setConnectionManager(connMgr)
                .build();

        HttpComponentsClientHttpRequestFactory httpsFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpsFactory.setReadTimeout(40000);
        httpsFactory.setConnectTimeout(40000);
        RestTemplate restTemplate = new RestTemplate(httpsFactory);
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName(CHARSET_NAME));

        // remove auto write accept charset
        messageConverter.setWriteAcceptCharset(false);
        restTemplate.getMessageConverters().add(messageConverter);
        return restTemplate;
    }

}
