package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Torre {
    private Integer idTorre;
    private String nombre;
    private Integer idCondominio;
}
