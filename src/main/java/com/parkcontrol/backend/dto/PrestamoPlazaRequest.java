package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoPlazaRequest {
    @NotNull
    private Integer idPropietario;
    @NotNull
    private Integer idUsuarioAutorizado;
    private String nombreUsuarioAutorizado;
    @NotNull
    private Integer idEstacionamiento;
    private String placaAutorizada;
    @NotNull
    private LocalDateTime fechaInicio;
    @NotNull
    private LocalDateTime fechaFin;
}
