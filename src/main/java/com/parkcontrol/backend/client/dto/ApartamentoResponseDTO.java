package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartamentoResponseDTO {
    private Long id;
    private String numero;
    private Double area;
    private String estado;
    private Long pisoId;
    private Integer pisoNumero;
    private Long torreId;
    private String torreNombre;
    private Long condominioId;
}
