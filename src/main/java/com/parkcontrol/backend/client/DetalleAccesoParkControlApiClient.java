package com.parkcontrol.backend.client;

import com.parkcontrol.backend.dto.DetalleAccesoParkControlRequest;
import com.parkcontrol.backend.model.DetalleAccesoParkControl;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;

@Component
public class DetalleAccesoParkControlApiClient {
    private final RestClient restClient;

    public DetalleAccesoParkControlApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<DetalleAccesoParkControl> findAll() {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public DetalleAccesoParkControl findById(Integer id) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public DetalleAccesoParkControl create(DetalleAccesoParkControlRequest request) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public DetalleAccesoParkControl update(Integer id, DetalleAccesoParkControlRequest request) {
        throw new UnsupportedOperationException("API not implemented yet");
    }

    public void delete(Integer id) {
        throw new UnsupportedOperationException("API not implemented yet");
    }
}
