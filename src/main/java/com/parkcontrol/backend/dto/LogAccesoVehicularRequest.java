package com.parkcontrol.backend.dto;

import java.time.LocalDateTime;

public record LogAccesoVehicularRequest(
    Integer idEstacionamiento,
    String matricula,
    String tipoOcupante,
    String metodo,
    LocalDateTime timestampEntrada,
    LocalDateTime timestampSalida
) {}
