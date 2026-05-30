package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZonaEstacionamiento {
    private Integer idZona;
    private String codigo;
    private String nombre;
    private Integer idCondominio;
}
