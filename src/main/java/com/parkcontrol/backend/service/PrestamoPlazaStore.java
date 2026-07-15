package com.parkcontrol.backend.service;

import com.parkcontrol.backend.model.PrestamoPlaza;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PrestamoPlazaStore {
    private final Map<Integer, PrestamoPlaza> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public PrestamoPlaza save(PrestamoPlaza prestamo) {
        if (prestamo.getIdPrestamo() == null || prestamo.getIdPrestamo() == 0) {
            prestamo.setIdPrestamo(idCounter.getAndIncrement());
        }
        store.put(prestamo.getIdPrestamo(), prestamo);
        return prestamo;
    }

    public PrestamoPlaza get(Integer id) {
        return store.get(id);
    }

    public List<PrestamoPlaza> getAll() {
        return List.copyOf(store.values());
    }

    public void remove(Integer id) {
        store.remove(id);
    }

    public PrestamoPlaza findByPlacaActiva(String placa) {
        LocalDateTime now = LocalDateTime.now();
        return store.values().stream()
                .filter(p -> "ACTIVO".equals(p.getEstado())
                        && placa.equalsIgnoreCase(p.getPlacaAutorizada())
                        && p.getFechaInicio() != null && p.getFechaInicio().isBefore(now)
                        && p.getFechaFin() != null && p.getFechaFin().isAfter(now))
                .findFirst().orElse(null);
    }

    public PrestamoPlaza findByIdEstacionamientoActivo(Integer idEstacionamiento) {
        LocalDateTime now = LocalDateTime.now();
        return store.values().stream()
                .filter(p -> "ACTIVO".equals(p.getEstado())
                        && idEstacionamiento.equals(p.getIdEstacionamiento())
                        && p.getFechaInicio() != null && p.getFechaInicio().isBefore(now)
                        && p.getFechaFin() != null && p.getFechaFin().isAfter(now))
                .findFirst().orElse(null);
    }
}
