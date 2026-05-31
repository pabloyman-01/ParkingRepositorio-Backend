package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.ZonaEstacionamientoResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.ZonaEstacionamientoRequest;
import com.parkcontrol.backend.model.ZonaEstacionamiento;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class ZonaEstacionamientoApiClient {
    private final RestClient restClient;

    public ZonaEstacionamientoApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<ZonaEstacionamiento> findAll() {
        List<ZonaEstacionamientoResponseDTO> dtos = restClient.get()
            .uri("/api/zonas-estacionamiento")
            .retrieve()
            .body(new ParameterizedTypeReference<List<ZonaEstacionamientoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toZonaEstacionamiento)
            .toList();
    }

    public ZonaEstacionamiento findById(Integer id) {
        ZonaEstacionamientoResponseDTO dto = restClient.get()
            .uri("/api/zonas-estacionamiento/{id}", id)
            .retrieve()
            .body(ZonaEstacionamientoResponseDTO.class);
        return MappingUtil.toZonaEstacionamiento(dto);
    }

    public ZonaEstacionamiento create(ZonaEstacionamientoRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.codigo(),
            "descripcion", request.nombre(),
            "estado", "ACTIVO",
            "condominioId", request.idCondominio()
        );
        ZonaEstacionamientoResponseDTO dto = restClient.post()
            .uri("/api/zonas-estacionamiento/create")
            .body(body)
            .retrieve()
            .body(ZonaEstacionamientoResponseDTO.class);
        return MappingUtil.toZonaEstacionamiento(dto);
    }

    public ZonaEstacionamiento update(Integer id, ZonaEstacionamientoRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.codigo(),
            "descripcion", request.nombre(),
            "estado", "ACTIVO",
            "condominioId", request.idCondominio()
        );
        ZonaEstacionamientoResponseDTO dto = restClient.put()
            .uri("/api/zonas-estacionamiento/{id}/update", id)
            .body(body)
            .retrieve()
            .body(ZonaEstacionamientoResponseDTO.class);
        return MappingUtil.toZonaEstacionamiento(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/zonas-estacionamiento/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
