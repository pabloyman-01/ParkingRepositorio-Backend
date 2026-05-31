package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.VehiculoResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.VehiculoRequest;
import com.parkcontrol.backend.model.Vehiculo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class VehiculoApiClient {
    private final RestClient restClient;

    public VehiculoApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Vehiculo> findAll() {
        List<VehiculoResponseDTO> dtos = restClient.get()
            .uri("/api/vehiculos")
            .retrieve()
            .body(new ParameterizedTypeReference<List<VehiculoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toVehiculo)
            .toList();
    }

    public Vehiculo findById(Integer id) {
        VehiculoResponseDTO dto = restClient.get()
            .uri("/api/vehiculos/{id}", id)
            .retrieve()
            .body(VehiculoResponseDTO.class);
        return MappingUtil.toVehiculo(dto);
    }

    public Vehiculo create(VehiculoRequest request) {
        Map<String, Object> body = Map.of(
            "placa", request.matricula(),
            "marca", "",
            "modelo", "",
            "color", "",
            "estado", "ACTIVO",
            "usuarioId", request.idUsuarioPropietario()
        );
        VehiculoResponseDTO dto = restClient.post()
            .uri("/api/vehiculos/create")
            .body(body)
            .retrieve()
            .body(VehiculoResponseDTO.class);
        return MappingUtil.toVehiculo(dto);
    }

    public Vehiculo update(Integer id, VehiculoRequest request) {
        Map<String, Object> body = Map.of(
            "placa", request.matricula(),
            "marca", "",
            "modelo", "",
            "color", "",
            "estado", "ACTIVO",
            "usuarioId", request.idUsuarioPropietario()
        );
        VehiculoResponseDTO dto = restClient.put()
            .uri("/api/vehiculos/{id}/update", id)
            .body(body)
            .retrieve()
            .body(VehiculoResponseDTO.class);
        return MappingUtil.toVehiculo(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/vehiculos/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
