package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PaseInvitadoRequest(
    String codigoPase,
    @NotBlank String matricula,
    @NotNull Integer idApartamento,
    Integer idUsuarioEmisor,
    @NotNull LocalDateTime fechaInicio,
    @NotNull LocalDateTime fechaFin,
    @NotBlank String estado,
    String nombreInvitado
) {}
