package com.parkcontrol.backend.client;

import com.parkcontrol.backend.config.ApiProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.Map;

@Component
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class AuthApiClient {
    private static final Logger log = LoggerFactory.getLogger(AuthApiClient.class);
    private final RestClient restClient;
    private String token;

    public AuthApiClient(ApiProperties apiProperties) {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
            .withConnectTimeout(Duration.ofSeconds(10))
            .withReadTimeout(Duration.ofSeconds(10));
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .requestFactory(ClientHttpRequestFactoryBuilder.detect().build(settings))
            .build();
    }

    @PostConstruct
    public void init() {
        try {
            login("admin@condosaas.com", "Admin123");
            log.info("Autenticacion exitosa en API Central");
        } catch (Exception e) {
            log.warn("No se pudo autenticar en API Central: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public String login(String email, String password) {
        Map<String, Object> response = restClient.post()
            .uri("/api/auth/login")
            .body(Map.of("email", email, "password", password))
            .retrieve()
            .body(Map.class);
        if (response != null && response.containsKey("token")) {
            this.token = (String) response.get("token");
        } else if (response != null && response.containsKey("accessToken")) {
            this.token = (String) response.get("accessToken");
        }
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthenticated() {
        return token != null && !token.isBlank();
    }
}
