package com.parkcontrol.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropietarioPlazaRequest {
    @NotNull
    private Integer idEstacionamiento;
    @NotNull
    private Integer idUsuario;
    private String nombreUsuario;
    private String placaVehiculo;
}
