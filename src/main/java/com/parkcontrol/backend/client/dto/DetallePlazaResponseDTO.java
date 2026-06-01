package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePlazaResponseDTO {
    private Long id;
    private String tipo;
    private String numeroPlaza;
    private String observaciones;
    private String estadoRegistro;
    private Long estacionamientoId;
    private String codigoEstacionamiento;
    private Long zonaId;
}
