package com.raven.security.user.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("raven.user")
@Getter
public class RavenAllowsUrlConfig {
    private List<String> allowList = new ArrayList<>();
}
