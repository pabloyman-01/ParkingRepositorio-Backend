package com.parkcontrol.backend.config;

import com.parkcontrol.backend.client.AuthInterceptor;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient apiRestClient(ApiProperties apiProperties, AuthInterceptor authInterceptor) {
        var settings = ClientHttpRequestFactorySettings.DEFAULTS
            .withConnectTimeout(Duration.ofSeconds(10))
            .withReadTimeout(Duration.ofSeconds(120));

        return RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .requestFactory(ClientHttpRequestFactories.get(settings))
            .requestInterceptor(authInterceptor)
            .build();
    }
}
