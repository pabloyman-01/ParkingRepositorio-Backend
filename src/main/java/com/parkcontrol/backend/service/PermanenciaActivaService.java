package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.provider.api.PermanenciaActivaApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermanenciaActivaService {
    private final PermanenciaActivaApiProvider apiProvider;

    public List<PermanenciaActiva> findAll() {
        return apiProvider.findAll();
    }

    public PermanenciaActiva findById(Integer id) {
        return apiProvider.findById(id);
    }

    public PermanenciaActiva create(PermanenciaActivaRequest request) {
        return apiProvider.create(request);
    }

    public PermanenciaActiva update(Integer id, PermanenciaActivaRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
