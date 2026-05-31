package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.TorreResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.TorreRequest;
import com.parkcontrol.backend.model.Torre;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class TorreApiClient {
    private final RestClient restClient;

    public TorreApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Torre> findAll() {
        List<TorreResponseDTO> dtos = restClient.get()
            .uri("/api/torres")
            .retrieve()
            .body(new ParameterizedTypeReference<List<TorreResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toTorre)
            .toList();
    }

    public Torre findById(Integer id) {
        TorreResponseDTO dto = restClient.get()
            .uri("/api/torres/{id}", id)
            .retrieve()
            .body(TorreResponseDTO.class);
        return MappingUtil.toTorre(dto);
    }

    public Torre create(TorreRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombre(),
            "estado", "ACTIVO",
            "condominioId", request.idCondominio()
        );
        TorreResponseDTO dto = restClient.post()
            .uri("/api/torres/create")
            .body(body)
            .retrieve()
            .body(TorreResponseDTO.class);
        return MappingUtil.toTorre(dto);
    }

    public Torre update(Integer id, TorreRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombre(),
            "estado", "ACTIVO",
            "condominioId", request.idCondominio()
        );
        TorreResponseDTO dto = restClient.put()
            .uri("/api/torres/{id}/update", id)
            .body(body)
            .retrieve()
            .body(TorreResponseDTO.class);
        return MappingUtil.toTorre(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/torres/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
