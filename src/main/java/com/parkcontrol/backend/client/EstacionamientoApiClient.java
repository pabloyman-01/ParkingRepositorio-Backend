package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.EstacionamientoResponseDTO;
import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class EstacionamientoApiClient {
    private final RestClient restClient;

    public EstacionamientoApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<Estacionamiento> findAll() {
        List<EstacionamientoResponseDTO> dtos = restClient.get()
            .uri("/api/estacionamiento")
            .retrieve()
            .body(new ParameterizedTypeReference<List<EstacionamientoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toEstacionamiento)
            .toList();
    }

    public Estacionamiento findById(Integer id) {
        EstacionamientoResponseDTO dto = restClient.get()
            .uri("/api/estacionamiento/{id}", id)
            .retrieve()
            .body(EstacionamientoResponseDTO.class);
        return MappingUtil.toEstacionamiento(dto);
    }

    public Estacionamiento create(EstacionamientoRequest request) {
        Map<String, Object> body = Map.of(
            "codigo", request.codigoPlaza(),
            "estadoOcupacion", "LIBRE",
            "zonaEstacionamientoId", request.idApartamento()
        );
        EstacionamientoResponseDTO dto = restClient.post()
            .uri("/api/estacionamiento/create")
            .body(body)
            .retrieve()
            .body(EstacionamientoResponseDTO.class);
        return MappingUtil.toEstacionamiento(dto);
    }

    public Estacionamiento update(Integer id, EstacionamientoRequest request) {
        Map<String, Object> body = Map.of(
            "codigo", request.codigoPlaza(),
            "estadoOcupacion", "LIBRE",
            "zonaEstacionamientoId", request.idApartamento()
        );
        EstacionamientoResponseDTO dto = restClient.put()
            .uri("/api/estacionamiento/{id}/update", id)
            .body(body)
            .retrieve()
            .body(EstacionamientoResponseDTO.class);
        return MappingUtil.toEstacionamiento(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/estacionamiento/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
