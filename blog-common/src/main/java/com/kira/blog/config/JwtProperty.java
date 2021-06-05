package com.kira.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Kira
 */
@ConfigurationProperties(prefix = "blog.jwt")
@Data
@Component
public class JwtProperty {

    private String secret;

    private String rsaPubKey;

    private String rsaPriKey;

    private Long expire;

    private Long refreshExpire;

}
