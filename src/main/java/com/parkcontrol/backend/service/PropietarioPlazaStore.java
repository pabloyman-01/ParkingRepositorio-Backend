package com.parkcontrol.backend.service;

import com.parkcontrol.backend.model.PropietarioPlaza;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PropietarioPlazaStore {
    private final Map<Integer, PropietarioPlaza> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public PropietarioPlaza save(PropietarioPlaza propietario) {
        if (propietario.getIdPropietario() == null || propietario.getIdPropietario() == 0) {
            propietario.setIdPropietario(idCounter.getAndIncrement());
        }
        store.put(propietario.getIdPropietario(), propietario);
        return propietario;
    }

    public PropietarioPlaza get(Integer id) {
        return store.get(id);
    }

    public List<PropietarioPlaza> getAll() {
        return List.copyOf(store.values());
    }

    public void remove(Integer id) {
        store.remove(id);
    }

    public PropietarioPlaza findByIdEstacionamiento(Integer idEstacionamiento) {
        return store.values().stream()
                .filter(p -> idEstacionamiento.equals(p.getIdEstacionamiento()) && "ACTIVO".equals(p.getEstado()))
                .findFirst().orElse(null);
    }
}
