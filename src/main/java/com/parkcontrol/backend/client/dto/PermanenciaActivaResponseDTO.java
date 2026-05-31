package com.parkcontrol.backend.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermanenciaActivaResponseDTO {
    private Long id;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private String estado;
    private Long vehiculoId;
    private String placa;
    private Long logEntradaId;
    private Long logSalidaId;
}
