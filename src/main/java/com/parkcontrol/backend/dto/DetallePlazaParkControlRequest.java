package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetallePlazaParkControlRequest(
    @NotNull Integer idEstacionamiento,
    Integer idZona,
    @NotBlank String estadoOcupacion,
    Integer idVehiculoActual
) {}
