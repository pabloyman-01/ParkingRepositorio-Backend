package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PisoResponseDTO {
    private Long id;
    private Integer numero;
    private String estado;
    private Long torreId;
    private String torreNombre;
    private Long condominioId;
}
