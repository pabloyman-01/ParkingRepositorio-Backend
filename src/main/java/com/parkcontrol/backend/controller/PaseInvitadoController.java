package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import com.parkcontrol.backend.model.PaseInvitado;
import com.parkcontrol.backend.service.PaseInvitadoService;
import lombok.RequiredArgsConstructor;
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
}
