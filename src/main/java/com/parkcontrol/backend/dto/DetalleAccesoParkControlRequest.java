package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleAccesoParkControlRequest(
    @NotNull Integer idLogV,
    Integer idVehiculo,
    Integer idApartamento,
    @NotBlank String estadoRegistro,
    Integer duracionMinutos,
    String nombreResidente
) {}
