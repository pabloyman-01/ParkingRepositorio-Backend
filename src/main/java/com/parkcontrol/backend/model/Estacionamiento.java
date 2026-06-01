package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estacionamiento {
    private Integer idEstacionamiento;
    private String codigoPlaza;
    private String tipo;
    private Integer idApartamento;
}
