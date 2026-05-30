package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TorreResponseDTO {
    private Long id;
    private String nombre;
    private String estado;
    private Long condominioId;
    private String condominioNombre;
}
