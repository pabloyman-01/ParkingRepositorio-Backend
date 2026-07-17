package com.parkcontrol.backend.repository;

import com.parkcontrol.backend.entity.PrestamoPlazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PrestamoPlazaRepository extends JpaRepository<PrestamoPlazaEntity, Integer> {
    List<PrestamoPlazaEntity> findByIdPropietarioAndEstado(Integer idPropietario, String estado);

    Optional<PrestamoPlazaEntity> findByIdEstacionamientoAndEstadoAndFechaInicioBeforeAndFechaFinAfter(
            Integer idEstacionamiento, String estado, LocalDateTime now, LocalDateTime now2);

    Optional<PrestamoPlazaEntity> findFirstByIdEstacionamientoOrderByFechaInicioDesc(Integer idEstacionamiento);

    @Query("SELECT COUNT(p) > 0 FROM PrestamoPlazaEntity p WHERE p.idEstacionamiento = :id AND p.estado = 'ACTIVO' " +
           "AND p.fechaInicio < :fin AND p.fechaFin > :inicio " +
           "AND (:excludeId IS NULL OR p.idPrestamo <> :excludeId)")
    boolean existsOverlapping(@Param("id") Integer idEstacionamiento,
                              @Param("inicio") LocalDateTime inicio,
                              @Param("fin") LocalDateTime fin,
                              @Param("excludeId") Integer excludeId);
}
