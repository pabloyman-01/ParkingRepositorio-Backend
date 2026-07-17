package com.parkcontrol.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visitantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVisitante;
    private String nombre;
    private String placa;
    private Integer idPase;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
}
