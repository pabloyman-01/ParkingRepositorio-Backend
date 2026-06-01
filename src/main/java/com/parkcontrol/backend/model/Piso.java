package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Piso {
    private Integer idPiso;
    private Integer numeroPiso;
    private Integer idTorre;
}
