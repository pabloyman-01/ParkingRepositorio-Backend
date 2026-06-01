package com.parkcontrol.backend.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
    private Integer idRol;
    private String nombreRol;
}
