package com.parkcontrol.backend.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class AuthInterceptor implements ClientHttpRequestInterceptor {

    private final AuthApiClient authApiClient;

    public AuthInterceptor(AuthApiClient authApiClient) {
        this.authApiClient = authApiClient;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                         ClientHttpRequestExecution execution) throws IOException {
        if (authApiClient.isAuthenticated()) {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + authApiClient.getToken());
        }
        return execution.execute(request, body);
    }
}
