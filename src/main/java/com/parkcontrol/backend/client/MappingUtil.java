package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.*;
import com.parkcontrol.backend.model.*;
import java.math.BigDecimal;

public class MappingUtil {

    public static Condominio toCondominio(CondominioResponseDTO dto) {
        if (dto == null) return null;
        return Condominio.builder()
            .idCondominio(dto.getId() != null ? dto.getId().intValue() : null)
            .nombre(dto.getNombre())
            .direccion(dto.getDireccion())
            .build();
    }

    public static Torre toTorre(TorreResponseDTO dto) {
        if (dto == null) return null;
        return Torre.builder()
            .idTorre(dto.getId() != null ? dto.getId().intValue() : null)
            .nombre(dto.getNombre())
            .idCondominio(dto.getCondominioId() != null ? dto.getCondominioId().intValue() : null)
            .build();
    }

    public static Piso toPiso(PisoResponseDTO dto) {
        if (dto == null) return null;
        return Piso.builder()
            .idPiso(dto.getId() != null ? dto.getId().intValue() : null)
            .numeroPiso(dto.getNumero())
            .idTorre(dto.getTorreId() != null ? dto.getTorreId().intValue() : null)
            .build();
    }

    public static Apartamento toApartamento(ApartamentoResponseDTO dto) {
        if (dto == null) return null;
        return Apartamento.builder()
            .idApartamento(dto.getId() != null ? dto.getId().intValue() : null)
            .numero(dto.getNumero())
            .metraje(dto.getArea() != null ? BigDecimal.valueOf(dto.getArea()) : null)
            .derechoEstacionamiento("OCUPADO".equalsIgnoreCase(dto.getEstado()))
            .idPiso(dto.getPisoId() != null ? dto.getPisoId().intValue() : null)
            .build();
    }

    public static Rol toRol(RolResponseDTO dto) {
        if (dto == null) return null;
        return Rol.builder()
            .idRol(dto.getId() != null ? dto.getId().intValue() : null)
            .nombreRol(dto.getNombre())
            .build();
    }

    public static Usuario toUsuario(UsuarioResponseDTO dto) {
        if (dto == null) return null;
        return Usuario.builder()
            .idUsuario(dto.getId() != null ? dto.getId().intValue() : null)
            .nombreCompleto((dto.getNombres() != null ? dto.getNombres() : "") + " " + (dto.getApellidos() != null ? dto.getApellidos() : ""))
            .email(dto.getEmail())
            .passwordHash("")
            .idRol(dto.getRolId() != null ? dto.getRolId().intValue() : null)
            .idCondominio(dto.getCondominioId() != null ? dto.getCondominioId().intValue() : null)
            .condominioNombre(dto.getCondominioNombre())
            .unidad(dto.getUnidad())
            .build();
    }

    public static Vehiculo toVehiculo(VehiculoResponseDTO dto) {
        if (dto == null) return null;
        return Vehiculo.builder()
            .idVehiculo(dto.getId() != null ? dto.getId().intValue() : null)
            .matricula(dto.getPlaca())
            .marcaModelo((dto.getMarca() != null ? dto.getMarca() : "") + " " + (dto.getModelo() != null ? dto.getModelo() : ""))
            .marca(dto.getMarca())
            .modelo(dto.getModelo())
            .color(dto.getColor())
            .tipoRegistro(dto.getTipoOcupante() != null ? dto.getTipoOcupante() : "Propietario")
            .tipoOcupante(dto.getTipoOcupante())
            .estado(dto.getEstado())
            .propietarioNombre(dto.getUsuarioNombre())
            .idApartamento(dto.getApartamentoId() != null ? dto.getApartamentoId().intValue() : null)
            .idUsuarioPropietario(dto.getUsuarioId() != null ? dto.getUsuarioId().intValue() : null)
            .unidad(dto.getUnidad())
            .pisoNumero(dto.getPisoNumero())
            .torreNombre(dto.getTorreNombre())
            .condominioNombre(dto.getCondominioNombre())
            .build();
    }

    public static Estacionamiento toEstacionamiento(EstacionamientoResponseDTO dto) {
        if (dto == null) return null;
        return Estacionamiento.builder()
            .idEstacionamiento(dto.getId() != null ? dto.getId().intValue() : null)
            .codigoPlaza(dto.getCodigo())
            .tipo(dto.getEstadoOcupacion())
            .estadoOcupacion(dto.getEstadoOcupacion())
            // Spec V6: tipo de vehículo, cupo y ocupación de la plaza (para el mapa).
            .tipoVehiculo(dto.getTipoVehiculo())
            .capacidad(dto.getCapacidad())
            .ocupacionActual(dto.getOcupacionActual())
            .apartamentoNumero(dto.getApartamentoNumero())
            .idApartamento(dto.getApartamentoId() != null ? dto.getApartamentoId().intValue() : null)
            .zonaNombre(dto.getZonaNombre())
            .zonaEstacionamientoId(dto.getZonaEstacionamientoId() != null ? dto.getZonaEstacionamientoId().intValue() : null)
            .idCondominio(dto.getCondominioId() != null ? dto.getCondominioId().intValue() : null)
            .condominioNombre(dto.getCondominioNombre())
            .idVehiculoActual(dto.getVehiculoActualId() != null ? dto.getVehiculoActualId().intValue() : null)
            .placaActual(dto.getPlacaActual())
            .build();
    }

    public static ZonaEstacionamiento toZonaEstacionamiento(ZonaEstacionamientoResponseDTO dto) {
        if (dto == null) return null;
        return ZonaEstacionamiento.builder()
            .idZona(dto.getId() != null ? dto.getId().intValue() : null)
            .codigo(dto.getNombre())
            .nombre(dto.getDescripcion())
            .idCondominio(dto.getCondominioId() != null ? dto.getCondominioId().intValue() : null)
            .build();
    }

    public static DetallePlazaParkControl toDetallePlaza(DetallePlazaResponseDTO dto) {
        if (dto == null) return null;
        return DetallePlazaParkControl.builder()
            .idDetalle(dto.getId() != null ? dto.getId().intValue() : null)
            .idEstacionamiento(dto.getEstacionamientoId() != null ? dto.getEstacionamientoId().intValue() : null)
            .idZona(dto.getZonaId() != null ? dto.getZonaId().intValue() : null)
            .estadoOcupacion(dto.getEstadoRegistro())
            .build();
    }

    public static PermanenciaActiva toPermanenciaActiva(PermanenciaActivaResponseDTO dto) {
        if (dto == null) return null;
        return PermanenciaActiva.builder()
            .idPermanencia(dto.getId() != null ? dto.getId().intValue() : null)
            .idLogV(dto.getLogEntradaId() != null ? dto.getLogEntradaId().intValue() : null)
            .placa(dto.getPlaca())
            .horaEntrada(dto.getFechaEntrada())
            .horaSalida(dto.getFechaSalida())
            .estado(dto.getEstado())
            .idVehiculo(dto.getVehiculoId() != null ? dto.getVehiculoId().intValue() : null)
            .build();
    }

    public static PaseInvitado toPaseInvitado(PaseInvitadoResponseDTO dto) {
        if (dto == null) return null;
        return PaseInvitado.builder()
            .idPase(dto.getId() != null ? dto.getId().intValue() : null)
            .codigoPase(dto.getCodigo())
            .matricula("")
            .idApartamento(null)
            .idUsuarioEmisor(dto.getUsuarioId() != null ? dto.getUsuarioId().intValue() : null)
            .fechaInicio(dto.getFechaInicio())
            .fechaFin(dto.getFechaFin())
            .estado(dto.getEstado())
            .nombreInvitado(dto.getNombreInvitado())
            .build();
    }

    public static LogAccesoVehicular toLogAccesoVehicular(LogAccesoVehicularResponseDTO dto) {
        if (dto == null) return null;
        return LogAccesoVehicular.builder()
            .idLogV(dto.getId() != null ? dto.getId().intValue() : null)
            .matricula(dto.getPlaca())
            .tipoOcupante(dto.getTipo())
            .metodo(dto.getMetodo())
            .timestampEntrada(dto.getFechaHora())
            .build();
    }
}
