package com.parkcontrol.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos_plaza")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoPlazaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;
    private Integer idPropietario;
    private Integer idUsuarioAutorizado;
    private String nombreUsuarioAutorizado;
    private Integer idEstacionamiento;
    private String placaAutorizada;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
}
