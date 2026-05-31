package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.model.LogAccesoVehicular;
import com.parkcontrol.backend.service.LogAccesoVehicularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs-acceso")
@RequiredArgsConstructor
public class LogAccesoVehicularController {
    private final LogAccesoVehicularService service;

    @GetMapping
    public ApiResponse<List<LogAccesoVehicular>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<LogAccesoVehicular> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<LogAccesoVehicular> create(@RequestBody @Valid LogAccesoVehicularRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<LogAccesoVehicular> update(@PathVariable Integer id, @RequestBody @Valid LogAccesoVehicularRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
