package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PermanenciaActivaRequest(
    @NotNull Integer idLogV,
    Integer idEstacionamiento,
    Integer idApartamento,
    @NotBlank String placa,
    @NotNull LocalDateTime horaEntrada
) {}
