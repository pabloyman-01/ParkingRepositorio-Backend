package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.PermanenciaActivaRequest;
import com.parkcontrol.backend.model.PermanenciaActiva;
import com.parkcontrol.backend.service.PermanenciaActivaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permanencias-activas")
@RequiredArgsConstructor
public class PermanenciaActivaController {
    private final PermanenciaActivaService service;

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
}
