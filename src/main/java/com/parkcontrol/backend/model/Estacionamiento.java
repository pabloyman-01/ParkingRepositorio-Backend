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
    // Datos enriquecidos (de la API Central) para el mapa tipo plano.
    private String estadoOcupacion;
    private String zonaNombre;
    private Integer zonaEstacionamientoId;
    private Integer idCondominio;
    private String condominioNombre;
    private Integer idVehiculoActual;
    private String placaActual;
    // Datos enriquecidos (del store local) para propietario y ocupacion.
    private Integer propietarioId;
    private String propietarioNombre;
    private String ocupanteNombre;
    private String tipoUso; // PROPIO, PRESTAMO, VISITANTE, null
    private Integer prestamoId;
    private Boolean prestamoExpirado;
}
