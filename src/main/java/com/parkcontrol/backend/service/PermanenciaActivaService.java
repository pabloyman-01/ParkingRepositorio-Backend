package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.provider.api.PermanenciaActivaApiProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermanenciaActivaService {
    private final PermanenciaActivaApiProvider apiProvider;
    private final LogAccesoVehicularService logService;

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

    public PermanenciaActiva registrarEntrada(Map<String, Object> body) {
        PermanenciaActiva result = apiProvider.registrarEntrada(body);
        try {
            logService.create(new LogAccesoVehicularRequest(
                    null,
                    (String) body.getOrDefault("placa", ""),
                    "ENTRADA",
                    (String) body.getOrDefault("metodo", "MANUAL"),
                    LocalDateTime.now(),
                    null
            ));
        } catch (Exception e) {
            log.warn("No se pudo registrar el log de entrada vehicular: {}", e.getMessage());
        }
        return result;
    }

    public PermanenciaActiva registrarSalida(Map<String, Object> body) {
        PermanenciaActiva result = apiProvider.registrarSalida(body);
        try {
            logService.create(new LogAccesoVehicularRequest(
                    null,
                    (String) body.getOrDefault("placa", ""),
                    "SALIDA",
                    (String) body.getOrDefault("metodo", "MANUAL"),
                    LocalDateTime.now(),
                    null
            ));
        } catch (Exception e) {
            log.warn("No se pudo registrar el log de salida vehicular: {}", e.getMessage());
        }
        return result;
    }
}