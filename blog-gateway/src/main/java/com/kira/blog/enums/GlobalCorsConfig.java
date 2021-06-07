package com.kira.blog.enums;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * @author Kira
 */
@Configuration
@ConfigurationProperties(prefix = "blog.filter")
public class GlobalCorsConfig {
    private List<String> allowOrigins;

    public List<String> getAllowOrigins() {
        return allowOrigins;
    }

    public void setAllowOrigins(List<String> allowOrigins) {
        this.allowOrigins = allowOrigins;
    }

    @Bean
    public CorsFilter corsFilter() {

        // 1. add the cors configuration info
        CorsConfiguration config = new CorsConfiguration();

        // (1) add the accept com.kira.common.domain, do not use *, else cookie cannot be used
        if (allowOrigins.size() != 0) {
            allowOrigins.forEach(config::addAllowedOrigin);
        }
        // (2) set the accept request method
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);

        // (3) set the accept header info
        config.addAllowedHeader("*");

        // (4) set whether could send cookie info
        config.setAllowCredentials(true);

        // 2. add the map url, intercept all the request
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        // 3. return the new CorsFilter
        return new CorsFilter(configSource);
    }
}
