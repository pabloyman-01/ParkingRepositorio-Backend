package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.VehiculoRequest;
import com.parkcontrol.backend.model.Vehiculo;
import com.parkcontrol.backend.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    private final VehiculoService service;

    @GetMapping
    public ApiResponse<List<Vehiculo>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Vehiculo> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Vehiculo> create(@RequestBody @Valid VehiculoRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Vehiculo> update(@PathVariable Integer id, @RequestBody @Valid VehiculoRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
