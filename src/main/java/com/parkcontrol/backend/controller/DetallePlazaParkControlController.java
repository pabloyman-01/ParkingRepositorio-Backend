package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.DetallePlazaParkControlRequest;
import com.parkcontrol.backend.model.DetallePlazaParkControl;
import com.parkcontrol.backend.service.DetallePlazaParkControlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-plaza")
@RequiredArgsConstructor
public class DetallePlazaParkControlController {
    private final DetallePlazaParkControlService service;

    @GetMapping
    public ApiResponse<List<DetallePlazaParkControl>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<DetallePlazaParkControl> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DetallePlazaParkControl> create(@RequestBody @Valid DetallePlazaParkControlRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<DetallePlazaParkControl> update(@PathVariable Integer id, @RequestBody @Valid DetallePlazaParkControlRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
