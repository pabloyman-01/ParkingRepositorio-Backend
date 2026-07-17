package com.parkcontrol.backend.repository;

import com.parkcontrol.backend.entity.VisitanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitanteRepository extends JpaRepository<VisitanteEntity, Integer> {
    Optional<VisitanteEntity> findFirstByPlacaOrderByFechaEntradaDesc(String placa);
}
