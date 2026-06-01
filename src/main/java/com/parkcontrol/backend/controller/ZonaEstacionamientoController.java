package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.ZonaEstacionamientoRequest;
import com.parkcontrol.backend.model.ZonaEstacionamiento;
import com.parkcontrol.backend.service.ZonaEstacionamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas-estacionamiento")
@RequiredArgsConstructor
public class ZonaEstacionamientoController {
    private final ZonaEstacionamientoService service;

    @GetMapping
    public ApiResponse<List<ZonaEstacionamiento>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ZonaEstacionamiento> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ZonaEstacionamiento> create(@RequestBody @Valid ZonaEstacionamientoRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ZonaEstacionamiento> update(@PathVariable Integer id, @RequestBody @Valid ZonaEstacionamientoRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
