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
        store.remove(id);
    }

    public PropietarioPlaza findByIdEstacionamiento(Integer idEstacionamiento) {
        return store.findByIdEstacionamiento(idEstacionamiento);
    }
}
