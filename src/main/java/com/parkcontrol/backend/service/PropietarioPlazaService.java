package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.PropietarioPlazaRequest;
import com.parkcontrol.backend.model.PropietarioPlaza;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropietarioPlazaService {
    private final PropietarioPlazaStore store;
    private final PrestamoPlazaStore prestamoStore;

    public List<PropietarioPlaza> findAll() {
        return store.getAll();
    }

    public PropietarioPlaza findById(Integer id) {
        return store.get(id);
    }

    public PropietarioPlaza create(PropietarioPlazaRequest request) {
        PropietarioPlaza propietario = PropietarioPlaza.builder()
                .idEstacionamiento(request.getIdEstacionamiento())
                .idUsuario(request.getIdUsuario())
                .nombreUsuario(request.getNombreUsuario())
                .placaVehiculo(request.getPlacaVehiculo())
                .fechaAsignacion(LocalDateTime.now())
                .estado("ACTIVO")
                .build();
        return store.save(propietario);
    }

    public void remove(Integer id) {
        PropietarioPlaza prop = store.get(id);
        if (prop != null) {
            List<com.parkcontrol.backend.model.PrestamoPlaza> activos = prestamoStore.getAll().stream()
                    .filter(p -> p.getIdPropietario().equals(id) && "ACTIVO".equals(p.getEstado()))
                    .toList();
            for (var p : activos) {
                p.setEstado("CANCELADO");
                prestamoStore.save(p);
            }
        }
        store.remove(id);
    }

    public PropietarioPlaza update(Integer id, PropietarioPlazaRequest request) {
        PropietarioPlaza existing = store.get(id);
        if (existing == null) return null;
        existing.setIdEstacionamiento(request.getIdEstacionamiento());
        existing.setIdUsuario(request.getIdUsuario());
        existing.setNombreUsuario(request.getNombreUsuario());
        existing.setPlacaVehiculo(request.getPlacaVehiculo());
        return store.save(existing);
    }

    public PropietarioPlaza findByIdEstacionamiento(Integer idEstacionamiento) {
        return store.findByIdEstacionamiento(idEstacionamiento);
    }
}
