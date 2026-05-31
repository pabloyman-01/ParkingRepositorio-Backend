package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record VehiculoRequest(
    @NotBlank String matricula,
    String marcaModelo,
    @NotBlank String tipoRegistro,
    @NotBlank String estado,
    LocalDateTime fechaExpiracion,
    String propietarioNombre,
    @NotNull Integer idApartamento,
    Integer idUsuarioPropietario
) {}
