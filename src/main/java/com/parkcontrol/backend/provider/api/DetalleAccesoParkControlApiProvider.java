package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.DetalleAccesoParkControlRequest;
import com.parkcontrol.backend.model.DetalleAccesoParkControl;
import com.parkcontrol.backend.client.DetalleAccesoParkControlApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DetalleAccesoParkControlApiProvider {
    private final DetalleAccesoParkControlApiClient client;

    public List<DetalleAccesoParkControl> findAll() {
        return client.findAll();
    }

    public DetalleAccesoParkControl findById(Integer id) {
        return client.findById(id);
    }

    public DetalleAccesoParkControl create(DetalleAccesoParkControlRequest request) {
        return client.create(request);
    }

    public DetalleAccesoParkControl update(Integer id, DetalleAccesoParkControlRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
