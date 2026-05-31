package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.VehiculoRequest;
import com.parkcontrol.backend.model.Vehiculo;
import com.parkcontrol.backend.provider.api.VehiculoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    private final VehiculoApiProvider apiProvider;

    public List<Vehiculo> findAll() {
        return apiProvider.findAll();
    }

    public Vehiculo findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Vehiculo create(VehiculoRequest request) {
        return apiProvider.create(request);
    }

    public Vehiculo update(Integer id, VehiculoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
