package com.parkcontrol.backend.dto;

public record UsuarioRequest(
    String nombreCompleto,
    String email,
    String passwordHash,
    Integer idRol,
    Integer idCondominio
) {}
