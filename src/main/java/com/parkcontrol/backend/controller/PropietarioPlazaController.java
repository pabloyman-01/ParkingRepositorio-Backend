package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.PropietarioPlazaRequest;
import com.parkcontrol.backend.model.PropietarioPlaza;
import com.parkcontrol.backend.service.PropietarioPlazaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios-plaza")
@RequiredArgsConstructor
public class PropietarioPlazaController {
    private final PropietarioPlazaService service;

    @GetMapping
    public ApiResponse<List<PropietarioPlaza>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PropietarioPlaza> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PropietarioPlaza> create(@RequestBody @Valid PropietarioPlazaRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> remove(@PathVariable Integer id) {
        service.remove(id);
        return ApiResponse.ok(null);
    }
}
