package com.parkcontrol.backend.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
    private final AuthApiClient authApiClient;

    public AuthInterceptor(AuthApiClient authApiClient) {
        this.authApiClient = authApiClient;
    }

    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                         @NonNull ClientHttpRequestExecution execution) throws IOException {
        if (authApiClient.isAuthenticated()) {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + authApiClient.getToken());
        }
        ClientHttpResponse response = execution.execute(request, body);
        if (response.getStatusCode() == HttpStatusCode.valueOf(401)) {
            log.warn("Token expirado, reautenticando...");
            authApiClient.login("admin@condosaas.com", "Admin123");
            if (authApiClient.isAuthenticated()) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + authApiClient.getToken());
                response.close();
                return execution.execute(request, body);
            }
        }
        return response;
    }
}
