package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.DetalleAccesoParkControlRequest;
import com.parkcontrol.backend.model.DetalleAccesoParkControl;
import com.parkcontrol.backend.service.DetalleAccesoParkControlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-acceso")
@RequiredArgsConstructor
public class DetalleAccesoParkControlController {
    private final DetalleAccesoParkControlService service;

    @GetMapping
    public ApiResponse<List<DetalleAccesoParkControl>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<DetalleAccesoParkControl> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DetalleAccesoParkControl> create(@RequestBody @Valid DetalleAccesoParkControlRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<DetalleAccesoParkControl> update(@PathVariable Integer id, @RequestBody @Valid DetalleAccesoParkControlRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
