package com.application.inditex.prices.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "test")
public class TestConfigurationProperties {

    private String protocol;

    private String host;

    private String port;

    private String domain;

    public String getUri() {
        return this.protocol + "://" + this.host + ":" + this.port + "/" + this.domain + "/";
    }
}
