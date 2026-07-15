package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.PrestamoPlazaRequest;
import com.parkcontrol.backend.model.PrestamoPlaza;
import com.parkcontrol.backend.service.PrestamoPlazaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos-plaza")
@RequiredArgsConstructor
public class PrestamoPlazaController {
    private final PrestamoPlazaService service;

    @GetMapping
    public ApiResponse<List<PrestamoPlaza>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PrestamoPlaza> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PrestamoPlaza> create(@RequestBody @Valid PrestamoPlazaRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<PrestamoPlaza> update(@PathVariable Integer id, @RequestBody @Valid PrestamoPlazaRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }

    @PostMapping("/{id}/finalizar")
    public ApiResponse<PrestamoPlaza> finalizar(@PathVariable Integer id) {
        return ApiResponse.ok(service.finalizar(id));
    }
}
