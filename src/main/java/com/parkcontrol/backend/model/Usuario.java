package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private Integer idUsuario;
    private String nombreCompleto;
    private String email;
    private String passwordHash;
    private Integer idRol;
    private Integer idCondominio;
}
