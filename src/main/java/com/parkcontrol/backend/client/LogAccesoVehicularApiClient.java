package com.parkcontrol.backend.client;

import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.model.LogAccesoVehicular;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;

@Component
public class LogAccesoVehicularApiClient {
    private final RestClient restClient;

    public LogAccesoVehicularApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<LogAccesoVehicular> findAll() {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public LogAccesoVehicular findById(Integer id) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public LogAccesoVehicular create(LogAccesoVehicularRequest request) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public LogAccesoVehicular update(Integer id, LogAccesoVehicularRequest request) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public void delete(Integer id) {
        throw new UnsupportedOperationException("API not implemented yet");
    }
}
