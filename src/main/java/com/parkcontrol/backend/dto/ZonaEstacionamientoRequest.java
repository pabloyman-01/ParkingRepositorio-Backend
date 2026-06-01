package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZonaEstacionamientoRequest(
    @NotBlank String codigo,
    String nombre,
    @NotNull Integer idCondominio
) {}
