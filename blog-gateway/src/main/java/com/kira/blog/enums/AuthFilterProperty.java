package com.kira.blog.enums;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "blog.filter")
@Data
public class AuthFilterProperty {

    private List<String> allowPaths;

}
