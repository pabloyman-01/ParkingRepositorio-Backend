package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.dto.PaseInvitadoRequest;
import com.parkcontrol.backend.model.PaseInvitado;
import com.parkcontrol.backend.service.PaseInvitadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pases-invitados")
@RequiredArgsConstructor
public class PaseInvitadoController {
    private final PaseInvitadoService service;

    @GetMapping
    public ApiResponse<List<PaseInvitado>> getAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PaseInvitado> getById(@PathVariable Integer id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PaseInvitado> create(@RequestBody @Valid PaseInvitadoRequest request) {
        return ApiResponse.created(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<PaseInvitado> update(@PathVariable Integer id, @RequestBody @Valid PaseInvitadoRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ApiResponse.ok(null);
    }
}
