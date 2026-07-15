package com.parkcontrol.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EstacionamientoRequest(
    @JsonProperty("codigo") String codigoPlaza,
    String tipo,
    String estadoOcupacion,
    @JsonProperty("zonaEstacionamientoId") Integer idApartamento,
    Integer idVehiculoActual
) {}
