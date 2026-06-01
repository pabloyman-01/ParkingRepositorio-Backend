package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.ZonaEstacionamientoRequest;
import com.parkcontrol.backend.model.ZonaEstacionamiento;
import com.parkcontrol.backend.provider.api.ZonaEstacionamientoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZonaEstacionamientoService {
    private final ZonaEstacionamientoApiProvider apiProvider;

    public List<ZonaEstacionamiento> findAll() {
        return apiProvider.findAll();
    }

    public ZonaEstacionamiento findById(Integer id) {
        return apiProvider.findById(id);
    }

    public ZonaEstacionamiento create(ZonaEstacionamientoRequest request) {
        return apiProvider.create(request);
    }

    public ZonaEstacionamiento update(Integer id, ZonaEstacionamientoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
