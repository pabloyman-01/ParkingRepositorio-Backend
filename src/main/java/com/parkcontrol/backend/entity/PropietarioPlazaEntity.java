package com.parkcontrol.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "propietarios_plaza")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropietarioPlazaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPropietario;
    private Integer idEstacionamiento;
    private Integer idUsuario;
    private String nombreUsuario;
    private String placaVehiculo;
    private LocalDateTime fechaAsignacion;
    private String estado;
}
