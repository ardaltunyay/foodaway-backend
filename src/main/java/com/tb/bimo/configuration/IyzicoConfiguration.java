package com.tb.bimo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "iyzipay-api")
public class IyzicoConfiguration {

    private String apiKey;
    private String secretKey;
    private String baseUrl;
}