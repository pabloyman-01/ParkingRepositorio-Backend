package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermanenciaActiva {
    private Integer idPermanencia;
    private Integer idLogV;
    private Integer idEstacionamiento;
    private Integer idApartamento;
    private String placa;
    private LocalDateTime horaEntrada;
}
