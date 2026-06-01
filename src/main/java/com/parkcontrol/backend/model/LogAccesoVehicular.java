package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAccesoVehicular {
    private Integer idLogV;
    private Integer idEstacionamiento;
    private String matricula;
    private String tipoOcupante;
    private String metodo;
    private LocalDateTime timestampEntrada;
    private LocalDateTime timestampSalida;
}
