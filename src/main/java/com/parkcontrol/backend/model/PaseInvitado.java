package com.parkcontrol.backend.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaseInvitado {
    private Integer idPase;
    private String codigoPase;
    private String matricula;
    private Integer idApartamento;
    private Integer idUsuarioEmisor;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
}
