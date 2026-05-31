package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.PermanenciaActivaResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class PermanenciaActivaApiClient {
    private final RestClient restClient;

    public PermanenciaActivaApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<PermanenciaActiva> findAll() {
        List<PermanenciaActivaResponseDTO> dtos = restClient.get()
            .uri("/api/permanencias")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PermanenciaActivaResponseDTO>>() {});
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
}
