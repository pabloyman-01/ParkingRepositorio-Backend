package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo {
    private Integer idVehiculo;
    private String matricula;
    private String marcaModelo;
    private String tipoRegistro;
    private String estado;
    private LocalDateTime fechaExpiracion;
    private String propietarioNombre;
    private Integer idApartamento;
    private Integer idUsuarioPropietario;
}
