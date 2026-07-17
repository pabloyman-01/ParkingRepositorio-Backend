package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visitante {
    private Integer idVisitante;
    private String nombre;
    private String placa;
    private Integer idPase;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
}
