package com.parkcontrol.backend.dto;

import java.math.BigDecimal;

public record ApartamentoRequest(
    String numero,
    BigDecimal metraje,
    Boolean derechoEstacionamiento,
    Integer idPiso
) {}
