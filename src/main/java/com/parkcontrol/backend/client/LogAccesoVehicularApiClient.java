package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.LogAccesoVehicularResponseDTO;
import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.model.LogAccesoVehicular;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("null") // Falsos positivos del null-analysis sobre la API de Spring RestClient
public class LogAccesoVehicularApiClient {
    private final RestClient restClient;

    public LogAccesoVehicularApiClient(RestClient apiRestClient) {
        this.restClient = apiRestClient;
    }

    public List<LogAccesoVehicular> findAll() {
        List<LogAccesoVehicularResponseDTO> dtos = restClient.get()
            .uri("/api/logs-acceso-vehicular")
            .retrieve()
            .body(new ParameterizedTypeReference<List<LogAccesoVehicularResponseDTO>>() {});
        if (dtos == null) return List.of();
        return dtos.stream()
            .map(MappingUtil::toLogAccesoVehicular)
            .toList();
    }

    public LogAccesoVehicular findById(Integer id) {
        LogAccesoVehicularResponseDTO dto = restClient.get()
            .uri("/api/logs-acceso-vehicular/{id}", id)
            .retrieve()
            .body(LogAccesoVehicularResponseDTO.class);
        return MappingUtil.toLogAccesoVehicular(dto);
    }

    public LogAccesoVehicular create(LogAccesoVehicularRequest request) {
        Map<String, Object> body = Map.of(
            "tipo", request.tipoOcupante(),
            "metodo", request.metodo(),
            "fechaHora", request.timestampEntrada().toString(),
            "observacion", "",
            "vehiculoId", request.idEstacionamiento(),
            "paseInvitadoId", null
        );
        LogAccesoVehicularResponseDTO dto = restClient.post()
            .uri("/api/logs-acceso-vehicular/create")
            .body(body)
            .retrieve()
            .body(LogAccesoVehicularResponseDTO.class);
        return MappingUtil.toLogAccesoVehicular(dto);
    }

    public LogAccesoVehicular update(Integer id, LogAccesoVehicularRequest request) {
        Map<String, Object> body = Map.of(
            "tipo", request.tipoOcupante(),
            "metodo", request.metodo(),
            "fechaHora", request.timestampEntrada().toString(),
            "observacion", "",
            "vehiculoId", request.idEstacionamiento(),
            "paseInvitadoId", null
        );
        LogAccesoVehicularResponseDTO dto = restClient.put()
            .uri("/api/logs-acceso-vehicular/{id}/update", id)
            .body(body)
            .retrieve()
            .body(LogAccesoVehicularResponseDTO.class);
        return MappingUtil.toLogAccesoVehicular(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/logs-acceso-vehicular/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
