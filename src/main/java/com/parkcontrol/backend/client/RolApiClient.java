package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.RolResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.RolRequest;
import com.parkcontrol.backend.model.Rol;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class RolApiClient {
    private final RestClient restClient;

    public RolApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Rol> findAll() {
        List<RolResponseDTO> dtos = restClient.get()
            .uri("/api/roles")
            .retrieve()
            .body(new ParameterizedTypeReference<List<RolResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toRol)
            .toList();
    }

    public Rol findById(Integer id) {
        RolResponseDTO dto = restClient.get()
            .uri("/api/roles/{id}", id)
            .retrieve()
            .body(RolResponseDTO.class);
        return MappingUtil.toRol(dto);
    }

    public Rol create(RolRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombreRol(),
            "descripcion", ""
        );
        RolResponseDTO dto = restClient.post()
            .uri("/api/roles/create")
            .body(body)
            .retrieve()
            .body(RolResponseDTO.class);
        return MappingUtil.toRol(dto);
    }

    public Rol update(Integer id, RolRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombreRol(),
            "descripcion", ""
        );
        RolResponseDTO dto = restClient.put()
            .uri("/api/roles/{id}/update", id)
            .body(body)
            .retrieve()
            .body(RolResponseDTO.class);
        return MappingUtil.toRol(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/roles/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
