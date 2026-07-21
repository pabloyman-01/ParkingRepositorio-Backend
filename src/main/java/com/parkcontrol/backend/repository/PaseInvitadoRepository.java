package com.parkcontrol.backend.repository;

import com.parkcontrol.backend.entity.PaseInvitadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaseInvitadoRepository extends JpaRepository<PaseInvitadoEntity, Integer> {
    Optional<PaseInvitadoEntity> findByIdPase(Integer idPase);
}
