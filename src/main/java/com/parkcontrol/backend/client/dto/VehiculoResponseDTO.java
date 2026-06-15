package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String estado;
    private Long usuarioId;
    private String usuarioNombre;
    // Datos enriquecidos que ya devuelve la API Central (ubicación del dueño).
    private String tipoOcupante;
    private Long apartamentoId;
    private String unidad;
    private Integer pisoNumero;
    private String torreNombre;
    private String condominioNombre;
}
