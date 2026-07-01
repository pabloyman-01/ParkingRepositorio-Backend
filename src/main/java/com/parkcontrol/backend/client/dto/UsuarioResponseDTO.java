package com.parkcontrol.backend.client.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String tipoOcupante;
    private String estado;
    private Long rolId;
    private String rolNombre;
    private Long apartamentoId;
    private Long condominioId;
    private String condominioNombre;
    private String unidad;
}
