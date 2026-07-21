package com.parkcontrol.backend.config;

import com.parkcontrol.backend.client.AuthInterceptor;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class RestClientConfig {

    @Bean
    public RestClient apiRestClient(ApiProperties apiProperties, AuthInterceptor authInterceptor) {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
            .withConnectTimeout(Duration.ofSeconds(10))
            .withReadTimeout(Duration.ofSeconds(30));

        return RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .requestFactory(ClientHttpRequestFactoryBuilder.detect().build(settings))
            .requestInterceptor(authInterceptor)
            .build();
    }
}
