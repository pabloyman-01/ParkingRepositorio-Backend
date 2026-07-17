package com.parkcontrol.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pases_invitados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaseInvitadoEntity {
    @Id
    private Integer idPase;
    private String codigoPase;
    private String matricula;
    private Integer idApartamento;
    private Integer idUsuarioEmisor;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
    private String nombreInvitado;
}
