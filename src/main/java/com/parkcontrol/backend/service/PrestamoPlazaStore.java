package com.parkcontrol.backend.service;

import com.parkcontrol.backend.entity.PrestamoPlazaEntity;
import com.parkcontrol.backend.model.PrestamoPlaza;
import com.parkcontrol.backend.repository.PrestamoPlazaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PrestamoPlazaStore {
    private final PrestamoPlazaRepository repo;

    public PrestamoPlaza save(PrestamoPlaza prestamo) {
        PrestamoPlazaEntity entity = toEntity(prestamo);
        PrestamoPlazaEntity saved = repo.save(entity);
        return toModel(saved);
    }

    public PrestamoPlaza get(Integer id) {
        return repo.findById(id).map(this::toModel).orElse(null);
    }

    public List<PrestamoPlaza> getAll() {
        return repo.findAll().stream().map(this::toModel).toList();
    }

    public void remove(Integer id) {
        repo.deleteById(id);
    }

    public PrestamoPlaza findByPlacaActiva(String placa) {
        return repo.findAll().stream()
                .filter(p -> "ACTIVO".equals(p.getEstado())
                        && placa.equalsIgnoreCase(p.getPlacaAutorizada())
                        && p.getFechaInicio() != null && p.getFechaInicio().isBefore(LocalDateTime.now())
                        && p.getFechaFin() != null && p.getFechaFin().isAfter(LocalDateTime.now()))
                .findFirst().map(this::toModel).orElse(null);
    }

    public PrestamoPlaza findByIdEstacionamientoActivo(Integer idEstacionamiento) {
        LocalDateTime now = LocalDateTime.now();
        return repo.findByIdEstacionamientoAndEstadoAndFechaInicioBeforeAndFechaFinAfter(
                idEstacionamiento, "ACTIVO", now, now)
                .map(this::toModel).orElse(null);
    }

    public PrestamoPlaza findByIdEstacionamientoCualquierEstado(Integer idEstacionamiento) {
        return repo.findFirstByIdEstacionamientoOrderByFechaInicioDesc(idEstacionamiento)
                .map(this::toModel).orElse(null);
    }

    public boolean existsOverlapping(Integer idEstacionamiento, LocalDateTime inicio, LocalDateTime fin, Integer excludeId) {
        return repo.existsOverlapping(idEstacionamiento, inicio, fin, excludeId);
    }

    public List<PrestamoPlaza> findActivosByIdPropietario(Integer idPropietario) {
        return repo.findByIdPropietarioAndEstado(idPropietario, "ACTIVO")
                .stream().map(this::toModel).toList();
    }

    private PrestamoPlazaEntity toEntity(PrestamoPlaza m) {
        return PrestamoPlazaEntity.builder()
                .idPrestamo(m.getIdPrestamo())
                .idPropietario(m.getIdPropietario())
                .idUsuarioAutorizado(m.getIdUsuarioAutorizado())
                .nombreUsuarioAutorizado(m.getNombreUsuarioAutorizado())
                .idEstacionamiento(m.getIdEstacionamiento())
                .placaAutorizada(m.getPlacaAutorizada())
                .fechaInicio(m.getFechaInicio())
                .fechaFin(m.getFechaFin())
                .estado(m.getEstado())
                .build();
    }

    private PrestamoPlaza toModel(PrestamoPlazaEntity e) {
        return PrestamoPlaza.builder()
                .idPrestamo(e.getIdPrestamo())
                .idPropietario(e.getIdPropietario())
                .idUsuarioAutorizado(e.getIdUsuarioAutorizado())
                .nombreUsuarioAutorizado(e.getNombreUsuarioAutorizado())
                .idEstacionamiento(e.getIdEstacionamiento())
                .placaAutorizada(e.getPlacaAutorizada())
                .fechaInicio(e.getFechaInicio())
                .fechaFin(e.getFechaFin())
                .estado(e.getEstado())
                .build();
    }
}
