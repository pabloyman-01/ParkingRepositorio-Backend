package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePlazaParkControl {
    private Integer idDetalle;
    private Integer idEstacionamiento;
    private Integer idZona;
    private String estadoOcupacion;
    private Integer idVehiculoActual;
}
