package com.santobucle.VaseDB.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private String vasedbClientUrl;
}
