package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Condominio {
    private Integer idCondominio;
    private String nombre;
    private String direccion;
}
