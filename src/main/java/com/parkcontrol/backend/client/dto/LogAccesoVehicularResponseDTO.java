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
public class LogAccesoVehicularResponseDTO {
    private Long id;
    private String tipo;
    private String metodo;
    private LocalDateTime fechaHora;
    private String observacion;
    private Long vehiculoId;
    private String placa;
    private Long paseInvitadoId;
}
