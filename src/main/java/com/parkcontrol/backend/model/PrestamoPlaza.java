package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoPlaza {
    private Integer idPrestamo;
    private Integer idPropietario;
    private Integer idUsuarioAutorizado;
    private String nombreUsuarioAutorizado;
    private Integer idEstacionamiento;
    private String placaAutorizada;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
}
