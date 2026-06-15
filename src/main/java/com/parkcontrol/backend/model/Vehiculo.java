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
    // Datos enriquecidos (de la API Central) para la ficha de control de acceso.
    private String marca;
    private String modelo;
    private String color;
    private String tipoOcupante;
    private String unidad;
    private Integer pisoNumero;
    private String torreNombre;
    private String condominioNombre;
}
