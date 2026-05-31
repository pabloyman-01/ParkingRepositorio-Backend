package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import com.parkcontrol.backend.client.EstacionamientoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstacionamientoApiProvider {
    private final EstacionamientoApiClient client;

    public List<Estacionamiento> findAll() {
        return client.findAll();
    }

    public Estacionamiento findById(Integer id) {
        return client.findById(id);
    }

    public Estacionamiento create(EstacionamientoRequest request) {
        return client.create(request);
    }

    public Estacionamiento update(Integer id, EstacionamientoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
