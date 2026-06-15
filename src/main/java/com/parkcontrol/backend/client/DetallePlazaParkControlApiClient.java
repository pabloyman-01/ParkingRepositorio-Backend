package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.DetallePlazaResponseDTO;
import com.parkcontrol.backend.dto.DetallePlazaParkControlRequest;
import com.parkcontrol.backend.model.DetallePlazaParkControl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class DetallePlazaParkControlApiClient {
    private final RestClient restClient;

    public DetallePlazaParkControlApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<DetallePlazaParkControl> findAll() {
        List<DetallePlazaResponseDTO> dtos = restClient.get()
            .uri("/api/detalles-plaza")
            .retrieve()
            .body(new ParameterizedTypeReference<List<DetallePlazaResponseDTO>>() {});
        if (dtos == null) return List.of();
        return dtos.stream()
            .map(MappingUtil::toDetallePlaza)
            .toList();
    }

    public DetallePlazaParkControl findById(Integer id) {
        DetallePlazaResponseDTO dto = restClient.get()
            .uri("/api/detalles-plaza/{id}", id)
            .retrieve()
            .body(DetallePlazaResponseDTO.class);
        return MappingUtil.toDetallePlaza(dto);
    }

    public DetallePlazaParkControl create(DetallePlazaParkControlRequest request) {
        Map<String, Object> body = Map.of(
            "tipo", "RESIDENTE",
            "numeroPlaza", request.estadoOcupacion(),
            "observaciones", "",
            "estadoRegistro", "ACTIVO",
            "estacionamientoId", request.idEstacionamiento()
        );
        DetallePlazaResponseDTO dto = restClient.post()
            .uri("/api/detalles-plaza/create")
            .body(body)
            .retrieve()
            .body(DetallePlazaResponseDTO.class);
        return MappingUtil.toDetallePlaza(dto);
    }

    public DetallePlazaParkControl update(Integer id, DetallePlazaParkControlRequest request) {
        Map<String, Object> body = Map.of(
            "tipo", "RESIDENTE",
            "numeroPlaza", request.estadoOcupacion(),
            "observaciones", "",
            "estadoRegistro", "ACTIVO",
            "estacionamientoId", request.idEstacionamiento()
        );
        DetallePlazaResponseDTO dto = restClient.put()
            .uri("/api/detalles-plaza/{id}/update", id)
            .body(body)
            .retrieve()
            .body(DetallePlazaResponseDTO.class);
        return MappingUtil.toDetallePlaza(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/detalles-plaza/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
