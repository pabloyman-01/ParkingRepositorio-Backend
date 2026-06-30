package com.parkcontrol.backend.dto;

public record EstacionamientoRequest(
    String codigoPlaza,
    String tipo,
    String estadoOcupacion,
    Integer idApartamento
) {}
