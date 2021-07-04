package com.kira.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "blog.filter")
@Data
public class AuthFilterProperty {

    private List<String> allowPaths;

}
