package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.PrestamoPlazaRequest;
import com.parkcontrol.backend.model.PrestamoPlaza;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrestamoPlazaService {
    private final PrestamoPlazaStore store;

    public List<PrestamoPlaza> findAll() {
        return store.getAll();
    }

    public PrestamoPlaza findById(Integer id) {
        return store.get(id);
    }

    public PrestamoPlaza create(PrestamoPlazaRequest request) {
        if (store.existsOverlapping(request.getIdEstacionamiento(), request.getFechaInicio(), request.getFechaFin(), null)) {
            throw new com.parkcontrol.backend.common.exception.BusinessException(
                    "La plaza ya tiene un prestamo activo en ese periodo", 409);
        }
        PrestamoPlaza prestamo = PrestamoPlaza.builder()
                .idPropietario(request.getIdPropietario())
                .idUsuarioAutorizado(request.getIdUsuarioAutorizado())
                .nombreUsuarioAutorizado(request.getNombreUsuarioAutorizado())
                .idEstacionamiento(request.getIdEstacionamiento())
                .placaAutorizada(request.getPlacaAutorizada())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .estado("ACTIVO")
                .build();
        return store.save(prestamo);
    }

    public PrestamoPlaza update(Integer id, PrestamoPlazaRequest request) {
        PrestamoPlaza existing = store.get(id);
        if (existing == null) return null;
        existing.setIdPropietario(request.getIdPropietario());
        existing.setIdUsuarioAutorizado(request.getIdUsuarioAutorizado());
        existing.setNombreUsuarioAutorizado(request.getNombreUsuarioAutorizado());
        existing.setIdEstacionamiento(request.getIdEstacionamiento());
        existing.setPlacaAutorizada(request.getPlacaAutorizada());
        existing.setFechaInicio(request.getFechaInicio());
        existing.setFechaFin(request.getFechaFin());
        return store.save(existing);
    }

    public void delete(Integer id) {
        store.remove(id);
    }

    public PrestamoPlaza finalizar(Integer id) {
        PrestamoPlaza prestamo = store.get(id);
        if (prestamo != null) {
            prestamo.setEstado("FINALIZADO");
            store.save(prestamo);
        }
        return prestamo;
    }

    public PrestamoPlaza findByPlacaActiva(String placa) {
        return store.findByPlacaActiva(placa);
    }
}
