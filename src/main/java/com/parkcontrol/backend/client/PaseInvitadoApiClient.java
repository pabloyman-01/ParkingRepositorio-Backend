package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.PaseInvitadoResponseDTO;
import com.parkcontrol.backend.dto.PaseInvitadoRequest;
import com.parkcontrol.backend.model.PaseInvitado;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class PaseInvitadoApiClient {
    private final RestClient restClient;

    public PaseInvitadoApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<PaseInvitado> findAll() {
        List<PaseInvitadoResponseDTO> dtos = restClient.get()
            .uri("/api/pases-invitado")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PaseInvitadoResponseDTO>>() {});
        if (dtos == null) return List.of();
        return dtos.stream()
            .map(MappingUtil::toPaseInvitado)
            .toList();
    }

    public PaseInvitado findById(Integer id) {
        PaseInvitadoResponseDTO dto = restClient.get()
            .uri("/api/pases-invitado/{id}", id)
            .retrieve()
            .body(PaseInvitadoResponseDTO.class);
        return MappingUtil.toPaseInvitado(dto);
    }

    public PaseInvitado create(PaseInvitadoRequest request) {
        Map<String, Object> body = Map.of(
            "codigo", request.codigoPase(),
            "nombreInvitado", "",
            "fechaInicio", request.fechaInicio(),
            "fechaFin", request.fechaFin(),
            "estado", request.estado(),
            "metodo", "MANUAL",
            "usuarioId", request.idUsuarioEmisor(),
            "vehiculoId", null
        );
        PaseInvitadoResponseDTO dto = restClient.post()
            .uri("/api/pases-invitado/create")
            .body(body)
            .retrieve()
            .body(PaseInvitadoResponseDTO.class);
        return MappingUtil.toPaseInvitado(dto);
    }

    public PaseInvitado update(Integer id, PaseInvitadoRequest request) {
        Map<String, Object> body = Map.of(
            "codigo", request.codigoPase(),
            "nombreInvitado", "",
            "fechaInicio", request.fechaInicio(),
            "fechaFin", request.fechaFin(),
            "estado", request.estado(),
            "metodo", "MANUAL",
            "usuarioId", request.idUsuarioEmisor(),
            "vehiculoId", null
        );
        PaseInvitadoResponseDTO dto = restClient.put()
            .uri("/api/pases-invitado/{id}/update", id)
            .body(body)
            .retrieve()
            .body(PaseInvitadoResponseDTO.class);
        return MappingUtil.toPaseInvitado(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/pases-invitado/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
