package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropietarioPlaza {
    private Integer idPropietario;
    private Integer idEstacionamiento;
    private Integer idUsuario;
    private String nombreUsuario;
    private String placaVehiculo;
    private LocalDateTime fechaAsignacion;
    private String estado;
}
