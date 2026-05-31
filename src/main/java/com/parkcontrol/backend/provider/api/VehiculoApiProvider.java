package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.VehiculoRequest;
import com.parkcontrol.backend.model.Vehiculo;
import com.parkcontrol.backend.client.VehiculoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VehiculoApiProvider {
    private final VehiculoApiClient client;

    public List<Vehiculo> findAll() {
        return client.findAll();
    }

    public Vehiculo findById(Integer id) {
        return client.findById(id);
    }

    public Vehiculo create(VehiculoRequest request) {
        return client.create(request);
    }

    public Vehiculo update(Integer id, VehiculoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
