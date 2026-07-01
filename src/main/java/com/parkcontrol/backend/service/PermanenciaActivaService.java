package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.provider.api.PermanenciaActivaApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PermanenciaActivaService {
    private final PermanenciaActivaApiProvider apiProvider;
    private final JdbcTemplate neonJdbcTemplate;

    public List<PermanenciaActiva> findAll() {
        return apiProvider.findAll();
    }

    public PermanenciaActiva findById(Integer id) {
        return apiProvider.findById(id);
    }

    public PermanenciaActiva create(PermanenciaActivaRequest request) {
        return apiProvider.create(request);
    }

    public PermanenciaActiva update(Integer id, PermanenciaActivaRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }

    public PermanenciaActiva registrarEntrada(Map<String, Object> body) {
        String placa = (String) body.get("placa");
        if (placa != null && !placa.isBlank()) {
            try {
                List<Integer> ids = neonJdbcTemplate.queryForList(
                    "SELECT id_vehiculo FROM vehiculo WHERE placa = ?", Integer.class, placa);
                if (!ids.isEmpty()) {
                    neonJdbcTemplate.update(
                        "INSERT INTO log_acceso_vehicular (tipo, metodo, fecha_hora, id_vehiculo) VALUES ('ENTRADA', 'MANUAL', NOW(), ?)",
                        ids.get(0));
                }
            } catch (Exception ignored) {}
        }
        return new PermanenciaActiva();
    }

    public PermanenciaActiva registrarSalida(Map<String, Object> body) {
        String placa = (String) body.get("placa");
        if (placa != null && !placa.isBlank()) {
            try {
                neonJdbcTemplate.update(
                    "INSERT INTO log_acceso_vehicular (tipo, metodo, fecha_hora, id_vehiculo) VALUES ('SALIDA', 'MANUAL', NOW(), (SELECT id_vehiculo FROM vehiculo WHERE placa = ?))",
                    placa);
            } catch (Exception ignored) {}
        }
        return new PermanenciaActiva();
    }
}