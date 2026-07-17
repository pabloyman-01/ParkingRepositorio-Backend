package com.parkcontrol.backend.service;

import com.parkcontrol.backend.entity.VisitanteEntity;
import com.parkcontrol.backend.model.Visitante;
import com.parkcontrol.backend.repository.VisitanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisitanteStore {
    private final VisitanteRepository repo;

    public Visitante save(Visitante visitante) {
        VisitanteEntity entity = VisitanteEntity.builder()
                .nombre(visitante.getNombre())
                .placa(visitante.getPlaca())
                .idPase(visitante.getIdPase())
                .fechaEntrada(visitante.getFechaEntrada())
                .fechaSalida(visitante.getFechaSalida())
                .build();
        VisitanteEntity saved = repo.save(entity);
        return toModel(saved);
    }

    public Visitante findByPlaca(String placa) {
        return repo.findFirstByPlacaOrderByFechaEntradaDesc(placa)
                .map(this::toModel).orElse(null);
    }

    private Visitante toModel(VisitanteEntity e) {
        return Visitante.builder()
                .idVisitante(e.getIdVisitante())
                .nombre(e.getNombre())
                .placa(e.getPlaca())
                .idPase(e.getIdPase())
                .fechaEntrada(e.getFechaEntrada())
                .fechaSalida(e.getFechaSalida())
                .build();
    }
}
