package com.parkcontrol.backend.service;

import com.parkcontrol.backend.entity.PropietarioPlazaEntity;
import com.parkcontrol.backend.model.PropietarioPlaza;
import com.parkcontrol.backend.repository.PropietarioPlazaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PropietarioPlazaStore {
    private final PropietarioPlazaRepository repo;

    public PropietarioPlaza save(PropietarioPlaza propietario) {
        PropietarioPlazaEntity entity = toEntity(propietario);
        PropietarioPlazaEntity saved = repo.save(entity);
        return toModel(saved);
    }

    public PropietarioPlaza get(Integer id) {
        return repo.findById(id).map(this::toModel).orElse(null);
    }

    public List<PropietarioPlaza> getAll() {
        return repo.findAll().stream().map(this::toModel).toList();
    }

    public void remove(Integer id) {
        repo.deleteById(id);
    }

    public PropietarioPlaza findByIdEstacionamiento(Integer idEstacionamiento) {
        return repo.findByIdEstacionamientoAndEstado(idEstacionamiento, "ACTIVO")
                .map(this::toModel).orElse(null);
    }

    private PropietarioPlazaEntity toEntity(PropietarioPlaza m) {
        return PropietarioPlazaEntity.builder()
                .idPropietario(m.getIdPropietario())
                .idEstacionamiento(m.getIdEstacionamiento())
                .idUsuario(m.getIdUsuario())
                .nombreUsuario(m.getNombreUsuario())
                .placaVehiculo(m.getPlacaVehiculo())
                .fechaAsignacion(m.getFechaAsignacion())
                .estado(m.getEstado())
                .build();
    }

    private PropietarioPlaza toModel(PropietarioPlazaEntity e) {
        return PropietarioPlaza.builder()
                .idPropietario(e.getIdPropietario())
                .idEstacionamiento(e.getIdEstacionamiento())
                .idUsuario(e.getIdUsuario())
                .nombreUsuario(e.getNombreUsuario())
                .placaVehiculo(e.getPlacaVehiculo())
                .fechaAsignacion(e.getFechaAsignacion())
                .estado(e.getEstado())
                .build();
    }
}
