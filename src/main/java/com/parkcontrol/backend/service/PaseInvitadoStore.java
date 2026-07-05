package com.parkcontrol.backend.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PaseInvitadoStore {
    private final Map<Integer, LocalPase> store = new ConcurrentHashMap<>();

    public void save(Integer id, String matricula, String nombreInvitado, Integer idApartamento) {
        store.put(id, new LocalPase(matricula, nombreInvitado, idApartamento));
    }

    public LocalPase get(Integer id) {
        return store.get(id);
    }

    public void remove(Integer id) {
        store.remove(id);
    }

    public record LocalPase(String matricula, String nombreInvitado, Integer idApartamento) {}
}
