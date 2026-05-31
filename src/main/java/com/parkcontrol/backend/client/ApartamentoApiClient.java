package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.ApartamentoResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.ApartamentoRequest;
import com.parkcontrol.backend.model.Apartamento;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class ApartamentoApiClient {
    private final RestClient restClient;

    public ApartamentoApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Apartamento> findAll() {
        List<ApartamentoResponseDTO> dtos = restClient.get()
            .uri("/api/apartamentos")
            .retrieve()
            .body(new ParameterizedTypeReference<List<ApartamentoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toApartamento)
            .toList();
    }

    public Apartamento findById(Integer id) {
        ApartamentoResponseDTO dto = restClient.get()
            .uri("/api/apartamentos/{id}", id)
            .retrieve()
            .body(ApartamentoResponseDTO.class);
        return MappingUtil.toApartamento(dto);
    }

    public Apartamento create(ApartamentoRequest request) {
        Map<String, Object> body = Map.of(
            "numero", request.numero(),
            "area", request.metraje().doubleValue(),
            "estado", "DISPONIBLE",
            "pisoId", request.idPiso()
        );
        ApartamentoResponseDTO dto = restClient.post()
            .uri("/api/apartamentos/create")
            .body(body)
            .retrieve()
            .body(ApartamentoResponseDTO.class);
        return MappingUtil.toApartamento(dto);
    }

    public Apartamento update(Integer id, ApartamentoRequest request) {
        Map<String, Object> body = Map.of(
            "numero", request.numero(),
            "area", request.metraje().doubleValue(),
            "estado", "DISPONIBLE",
            "pisoId", request.idPiso()
        );
        ApartamentoResponseDTO dto = restClient.put()
            .uri("/api/apartamentos/{id}/update", id)
            .body(body)
            .retrieve()
            .body(ApartamentoResponseDTO.class);
        return MappingUtil.toApartamento(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/apartamentos/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
