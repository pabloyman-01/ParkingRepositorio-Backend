package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.service.PermanenciaActivaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/permanencias-activas")
@RequiredArgsConstructor
public class PermanenciaActivaController {
    private final PermanenciaActivaService service;
    private final JdbcTemplate neonJdbcTemplate;

    @GetMapping
    public ApiResponse<List<PermanenciaActiva>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PermanenciaActiva> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PermanenciaActiva> create(@RequestBody @Valid PermanenciaActivaRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<PermanenciaActiva> update(@PathVariable Integer id, @RequestBody @Valid PermanenciaActivaRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }

    @PostMapping("/registrar-entrada")
    public ApiResponse<?> registrarEntrada(@RequestBody Map<String, Object> body) {
        String placa = (String) body.get("placa");
        if (placa == null || placa.isBlank()) {
            return ApiResponse.error("La placa es requerida");
        }
        try {
            List<Integer> ids = neonJdbcTemplate.queryForList(
                "SELECT id_vehiculo FROM vehiculo WHERE placa = ?", Integer.class, placa);
            if (ids.isEmpty()) {
                return ApiResponse.error("Vehículo no encontrado: " + placa);
            }
            Integer idVehiculo = ids.get(0);
            neonJdbcTemplate.update(
                "INSERT INTO log_acceso_vehicular (tipo, metodo, fecha_hora, id_vehiculo) VALUES ('ENTRADA', 'MANUAL', NOW(), ?)",
                idVehiculo);
            return ApiResponse.ok(Map.of("placa", placa, "estado", "ENTRADA_REGISTRADA"));
        } catch (Exception e) {
            return ApiResponse.error("Error al registrar entrada: " + e.getMessage());
        }
    }

    @PostMapping("/registrar-salida")
    public ApiResponse<?> registrarSalida(@RequestBody Map<String, Object> body) {
        String placa = (String) body.get("placa");
        if (placa == null || placa.isBlank()) {
            return ApiResponse.error("La placa es requerida");
        }
        try {
            neonJdbcTemplate.update(
                "INSERT INTO log_acceso_vehicular (tipo, metodo, fecha_hora, id_vehiculo) VALUES ('SALIDA', 'MANUAL', NOW(), (SELECT id_vehiculo FROM vehiculo WHERE placa = ?))",
                placa);
            return ApiResponse.ok(Map.of("placa", placa, "estado", "SALIDA_REGISTRADA"));
        } catch (Exception e) {
            return ApiResponse.error("Error al registrar salida: " + e.getMessage());
        }
    }
}