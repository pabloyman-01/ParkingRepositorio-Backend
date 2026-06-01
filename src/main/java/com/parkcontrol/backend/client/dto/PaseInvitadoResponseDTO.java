package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaseInvitadoResponseDTO {
    private Long id;
    private String codigo;
    private String nombreInvitado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
    private String metodo;
    private Long usuarioId;
    private String usuarioNombre;
    private Long vehiculoId;
}
