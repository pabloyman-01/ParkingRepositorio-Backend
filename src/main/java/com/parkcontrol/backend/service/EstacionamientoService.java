package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import com.parkcontrol.backend.provider.api.EstacionamientoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamientoService {
    private final EstacionamientoApiProvider apiProvider;
    private final JdbcTemplate neonJdbcTemplate;

    public List<Estacionamiento> findAll() {
        return apiProvider.findAll();
    }

    public Estacionamiento findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Estacionamiento create(EstacionamientoRequest request) {
        return apiProvider.create(request);
    }

    public Estacionamiento update(Integer id, EstacionamientoRequest request) {
        String estado = request.estadoOcupacion() != null ? request.estadoOcupacion() : "LIBRE";
        try {
            neonJdbcTemplate.update(
                "UPDATE estacionamiento SET estado_ocupacion = ? WHERE id_estacionamiento = ?",
                estado, id);
        } catch (Exception ignored) {}
        return new Estacionamiento();
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}