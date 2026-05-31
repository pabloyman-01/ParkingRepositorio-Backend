package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.PisoResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.PisoRequest;
import com.parkcontrol.backend.model.Piso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class PisoApiClient {
    private final RestClient restClient;

    public PisoApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Piso> findAll() {
        List<PisoResponseDTO> dtos = restClient.get()
            .uri("/api/pisos")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PisoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toPiso)
            .toList();
    }

    public Piso findById(Integer id) {
        PisoResponseDTO dto = restClient.get()
            .uri("/api/pisos/{id}", id)
            .retrieve()
            .body(PisoResponseDTO.class);
        return MappingUtil.toPiso(dto);
    }

    public Piso create(PisoRequest request) {
        Map<String, Object> body = Map.of(
            "numero", request.numeroPiso(),
            "estado", "ACTIVO",
            "torreId", request.idTorre()
        );
        PisoResponseDTO dto = restClient.post()
            .uri("/api/pisos/create")
            .body(body)
            .retrieve()
            .body(PisoResponseDTO.class);
        return MappingUtil.toPiso(dto);
    }

    public Piso update(Integer id, PisoRequest request) {
        Map<String, Object> body = Map.of(
            "numero", request.numeroPiso(),
            "estado", "ACTIVO",
            "torreId", request.idTorre()
        );
        PisoResponseDTO dto = restClient.put()
            .uri("/api/pisos/{id}/update", id)
            .body(body)
            .retrieve()
            .body(PisoResponseDTO.class);
        return MappingUtil.toPiso(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/pisos/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
