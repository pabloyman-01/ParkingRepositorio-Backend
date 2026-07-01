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
    private Long zonaEstacionamientoId;
    private String zonaNombre;
    private Long condominioId;
    // Datos enriquecidos que ya devuelve la API Central.
    private String condominioNombre;
    private Long vehiculoActualId;
    private String placaActual;
}
