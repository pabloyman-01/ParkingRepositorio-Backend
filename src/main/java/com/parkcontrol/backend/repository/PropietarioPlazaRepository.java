package com.parkcontrol.backend.repository;

import com.parkcontrol.backend.entity.PropietarioPlazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropietarioPlazaRepository extends JpaRepository<PropietarioPlazaEntity, Integer> {
    Optional<PropietarioPlazaEntity> findByIdEstacionamientoAndEstado(Integer idEstacionamiento, String estado);
}
