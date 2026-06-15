package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.PermanenciaActivaResponseDTO;
import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class PermanenciaActivaApiClient {
    private final RestClient restClient;

    public PermanenciaActivaApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<PermanenciaActiva> findAll() {
        List<PermanenciaActivaResponseDTO> dtos = restClient.get()
            .uri("/api/permanencias")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PermanenciaActivaResponseDTO>>() {});
        if (dtos == null) return List.of();
        return dtos.stream()
            .map(MappingUtil::toPermanenciaActiva)
            .toList();
    }

    public PermanenciaActiva findById(Integer id) {
        PermanenciaActivaResponseDTO dto = restClient.get()
            .uri("/api/permanencias/{id}", id)
            .retrieve()
            .body(PermanenciaActivaResponseDTO.class);
        return MappingUtil.toPermanenciaActiva(dto);
    }

    public PermanenciaActiva create(PermanenciaActivaRequest request) {
        Map<String, Object> body = Map.of(
            "fechaEntrada", request.horaEntrada(),
            "fechaSalida", null,
            "estado", "ACTIVA",
            "vehiculoId", request.idEstacionamiento(),
            "logEntradaId", request.idLogV(),
            "logSalidaId", null
        );
        PermanenciaActivaResponseDTO dto = restClient.post()
            .uri("/api/permanencias/create")
            .body(body)
            .retrieve()
            .body(PermanenciaActivaResponseDTO.class);
        return MappingUtil.toPermanenciaActiva(dto);
    }

    public PermanenciaActiva update(Integer id, PermanenciaActivaRequest request) {
        Map<String, Object> body = Map.of(
            "fechaEntrada", request.horaEntrada(),
            "fechaSalida", null,
            "estado", "ACTIVA",
            "vehiculoId", request.idEstacionamiento(),
            "logEntradaId", request.idLogV(),
            "logSalidaId", null
        );
        PermanenciaActivaResponseDTO dto = restClient.put()
            .uri("/api/permanencias/{id}/update", id)
            .body(body)
            .retrieve()
            .body(PermanenciaActivaResponseDTO.class);
        return MappingUtil.toPermanenciaActiva(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/permanencias/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }

    // Flujo de control de acceso: registra la entrada de un vehículo por su placa.
    public PermanenciaActiva registrarEntrada(Map<String, Object> body) {
        PermanenciaActivaResponseDTO dto = restClient.post()
            .uri("/api/permanencias/registrar-entrada")
            .body(body)
            .retrieve()
            .body(PermanenciaActivaResponseDTO.class);
        return MappingUtil.toPermanenciaActiva(dto);
    }

    // Flujo de control de acceso: registra la salida de un vehículo por su placa.
    public PermanenciaActiva registrarSalida(Map<String, Object> body) {
        PermanenciaActivaResponseDTO dto = restClient.post()
            .uri("/api/permanencias/registrar-salida")
            .body(body)
            .retrieve()
            .body(PermanenciaActivaResponseDTO.class);
        return MappingUtil.toPermanenciaActiva(dto);
    }
}
