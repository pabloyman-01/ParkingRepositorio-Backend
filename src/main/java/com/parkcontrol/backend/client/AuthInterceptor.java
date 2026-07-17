package com.parkcontrol.backend.client;

import com.parkcontrol.backend.config.ApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final String authEmail;
    private final String authPassword;

    public AuthInterceptor(AuthApiClient authApiClient,
                           @Value("${app.auth.email}") String authEmail,
                           @Value("${app.auth.password}") String authPassword) {
        this.authApiClient = authApiClient;
        this.authEmail = authEmail;
        this.authPassword = authPassword;
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
            authApiClient.login(authEmail, authPassword);
            if (authApiClient.isAuthenticated()) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + authApiClient.getToken());
                response.close();
                return execution.execute(request, body);
            }
        }
        return response;
    }
}
