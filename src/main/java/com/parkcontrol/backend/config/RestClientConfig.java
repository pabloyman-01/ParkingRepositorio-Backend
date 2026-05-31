package com.parkcontrol.backend.config;

import com.parkcontrol.backend.client.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient apiRestClient(ApiProperties apiProperties, AuthInterceptor authInterceptor) {
        return RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .requestInterceptor(authInterceptor)
            .build();
    }
}
