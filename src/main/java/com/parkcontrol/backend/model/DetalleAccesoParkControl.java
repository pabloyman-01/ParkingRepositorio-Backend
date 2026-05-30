package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleAccesoParkControl {
    private Integer idDetalleAcceso;
    private Integer idLogV;
    private Integer idVehiculo;
    private Integer idApartamento;
    private String estadoRegistro;
    private Integer duracionMinutos;
    private String nombreResidente;
}
