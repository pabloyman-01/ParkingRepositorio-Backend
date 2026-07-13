package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamientoResponseDTO {
    private Long id;
    private String codigo;
    private String estadoOcupacion;
    // Spec V6: tipo de vehículo (AUTO/MOTO), cupo y ocupación actual de la plaza.
    private String tipoVehiculo;
    private Integer capacidad;
    private Integer ocupacionActual;
    private Long apartamentoId;
    private String apartamentoNumero;
    private Long zonaEstacionamientoId;
    private String zonaNombre;
    private Long condominioId;
    // Datos enriquecidos que ya devuelve la API Central.
    private String condominioNombre;
    private Long vehiculoActualId;
    private String placaActual;
}
