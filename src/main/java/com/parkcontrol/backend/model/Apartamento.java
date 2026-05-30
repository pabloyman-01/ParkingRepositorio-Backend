package com.parkcontrol.backend.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apartamento {
    private Integer idApartamento;
    private String numero;
    private BigDecimal metraje;
    private Boolean derechoEstacionamiento;
    private Integer idPiso;
}
