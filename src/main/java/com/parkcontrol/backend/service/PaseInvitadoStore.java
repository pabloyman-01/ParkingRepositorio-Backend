package com.parkcontrol.backend.service;

import com.parkcontrol.backend.entity.PaseInvitadoEntity;
import com.parkcontrol.backend.repository.PaseInvitadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaseInvitadoStore {
    private final PaseInvitadoRepository repo;

    public void save(Integer id, String matricula, String nombreInvitado, Integer idApartamento) {
        repo.findById(id).ifPresent(entity -> {
            if (matricula != null && !matricula.isBlank()) entity.setMatricula(matricula);
            if (nombreInvitado != null) entity.setNombreInvitado(nombreInvitado);
            if (idApartamento != null) entity.setIdApartamento(idApartamento);
            repo.save(entity);
        });
    }

    public LocalPase get(Integer id) {
        return repo.findById(id).map(e -> new LocalPase(e.getMatricula(), e.getNombreInvitado(), e.getIdApartamento()))
                .orElse(null);
    }

    public void remove(Integer id) {
        repo.findById(id).ifPresent(entity -> {
            entity.setMatricula("");
            entity.setNombreInvitado("");
            entity.setIdApartamento(null);
            repo.save(entity);
        });
    }

    public record LocalPase(String matricula, String nombreInvitado, Integer idApartamento) {}
}
