package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.ZonaEstacionamientoRequest;
import com.parkcontrol.backend.model.ZonaEstacionamiento;
import com.parkcontrol.backend.client.ZonaEstacionamientoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ZonaEstacionamientoApiProvider {
    private final ZonaEstacionamientoApiClient client;

    public List<ZonaEstacionamiento> findAll() {
        return client.findAll();
    }

    public ZonaEstacionamiento findById(Integer id) {
        return client.findById(id);
    }

    public ZonaEstacionamiento create(ZonaEstacionamientoRequest request) {
        return client.create(request);
    }

    public ZonaEstacionamiento update(Integer id, ZonaEstacionamientoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
