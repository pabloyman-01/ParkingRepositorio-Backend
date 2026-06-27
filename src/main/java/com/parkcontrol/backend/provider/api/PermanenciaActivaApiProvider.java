package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.client.PermanenciaActivaApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PermanenciaActivaApiProvider {
    private final PermanenciaActivaApiClient client;

    public List<PermanenciaActiva> findAll() {
        return client.findAll();
    }

    public PermanenciaActiva findById(Integer id) {
        return client.findById(id);
    }

    public PermanenciaActiva create(PermanenciaActivaRequest request) {
        return client.create(request);
    }

    public PermanenciaActiva update(Integer id, PermanenciaActivaRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }

    public PermanenciaActiva registrarEntrada(Map<String, Object> body) {
        return client.registrarEntrada(body);
    }

    public PermanenciaActiva registrarSalida(Map<String, Object> body) {
        return client.registrarSalida(body);
    }
}
