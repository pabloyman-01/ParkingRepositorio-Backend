package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.TorreRequest;
import com.parkcontrol.backend.model.Torre;
import com.parkcontrol.backend.service.TorreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torres")
@RequiredArgsConstructor
public class TorreController {
    private final TorreService service;

    @GetMapping
    public ApiResponse<List<Torre>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Torre> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Torre> create(@RequestBody @Valid TorreRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Torre> update(@PathVariable Integer id, @RequestBody @Valid TorreRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
