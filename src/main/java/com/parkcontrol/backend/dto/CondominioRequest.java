package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record CondominioRequest(
    @NotBlank String nombre,
    String direccion
) {}
