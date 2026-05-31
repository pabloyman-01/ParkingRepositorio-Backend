package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.CondominioResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.CondominioRequest;
import com.parkcontrol.backend.model.Condominio;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class CondominioApiClient {
    private final RestClient restClient;

    public CondominioApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Condominio> findAll() {
        List<CondominioResponseDTO> dtos = restClient.get()
            .uri("/api/condominios")
            .retrieve()
            .body(new ParameterizedTypeReference<List<CondominioResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toCondominio)
            .toList();
    }

    public Condominio findById(Integer id) {
        CondominioResponseDTO dto = restClient.get()
            .uri("/api/condominios/{id}", id)
            .retrieve()
            .body(CondominioResponseDTO.class);
        return MappingUtil.toCondominio(dto);
    }

    public Condominio create(CondominioRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombre(),
            "direccion", request.direccion(),
            "telefono", "",
            "email", "",
            "estado", "ACTIVO"
        );
        CondominioResponseDTO dto = restClient.post()
            .uri("/api/condominios/create")
            .body(body)
            .retrieve()
            .body(CondominioResponseDTO.class);
        return MappingUtil.toCondominio(dto);
    }

    public Condominio update(Integer id, CondominioRequest request) {
        Map<String, Object> body = Map.of(
            "nombre", request.nombre(),
            "direccion", request.direccion(),
            "telefono", "",
            "email", "",
            "estado", "ACTIVO"
        );
        CondominioResponseDTO dto = restClient.put()
            .uri("/api/condominios/{id}/update", id)
            .body(body)
            .retrieve()
            .body(CondominioResponseDTO.class);
        return MappingUtil.toCondominio(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/condominios/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
