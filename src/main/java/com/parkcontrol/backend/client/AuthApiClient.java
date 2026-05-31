package com.parkcontrol.backend.client;

import com.parkcontrol.backend.config.ApiProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthApiClient {
    private final RestClient restClient;
    private String token;

    public AuthApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public String login(String email, String password) {
        throw new UnsupportedOperationException("Auth not implemented yet in API Central");
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
