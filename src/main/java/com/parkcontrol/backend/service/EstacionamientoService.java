package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import com.parkcontrol.backend.model.PrestamoPlaza;
import com.parkcontrol.backend.model.PropietarioPlaza;
import com.parkcontrol.backend.model.Vehiculo;
import com.parkcontrol.backend.model.Visitante;
import com.parkcontrol.backend.repository.PaseInvitadoRepository;
import com.parkcontrol.backend.provider.api.EstacionamientoApiProvider;
import com.parkcontrol.backend.provider.api.VehiculoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamientoService {
    private final EstacionamientoApiProvider apiProvider;
    private final PropietarioPlazaStore propietarioStore;
    private final PrestamoPlazaStore prestamoStore;
    private final VehiculoApiProvider vehiculoProvider;
    private final VisitanteStore visitanteStore;
    private final PaseInvitadoRepository paseInvitadoRepo;

    @Transactional(readOnly = true)
    public List<Estacionamiento> findAll() {
        List<Estacionamiento> plazas = apiProvider.findAll();
        var vehiculos = vehiculoProvider.findAll();
        // Cargar stores en mapas para evitar N+1 queries a Neon
        var propMap = propietarioStore.getAll().stream()
                .filter(p -> "ACTIVO".equals(p.getEstado()))
                .collect(java.util.stream.Collectors.toMap(
                        com.parkcontrol.backend.model.PropietarioPlaza::getIdEstacionamiento,
                        p -> p, (a, b) -> a));
        var prestMap = prestamoStore.getAll().stream()
                .collect(java.util.stream.Collectors.toMap(
                        com.parkcontrol.backend.model.PrestamoPlaza::getIdEstacionamiento,
                        p -> p, (a, b) -> a));
        for (Estacionamiento e : plazas) {
            enriquecer(e, vehiculos, propMap, prestMap);
        }
        return plazas;
    }

    public Estacionamiento findById(Integer id) {
        Estacionamiento e = apiProvider.findById(id);
        if (e != null) enriquecer(e, vehiculoProvider.findAll(),
                propietarioStore.getAll().stream()
                        .filter(p -> "ACTIVO".equals(p.getEstado()))
                        .collect(java.util.stream.Collectors.toMap(
                                com.parkcontrol.backend.model.PropietarioPlaza::getIdEstacionamiento,
                                p -> p, (a, b) -> a)),
                prestamoStore.getAll().stream()
                        .collect(java.util.stream.Collectors.toMap(
                                com.parkcontrol.backend.model.PrestamoPlaza::getIdEstacionamiento,
                                p -> p, (a, b) -> a)));
        return e;
    }

    public Estacionamiento create(EstacionamientoRequest request) {
        return apiProvider.create(request);
    }

    public Estacionamiento update(Integer id, EstacionamientoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }

    private String buscarNombreOcupante(Estacionamiento e, List<Vehiculo> vehiculos) {
        if (e.getPlacaActual() != null) {
            Visitante vis = visitanteStore.findByPlaca(e.getPlacaActual());
            if (vis != null && vis.getNombre() != null && !vis.getNombre().isBlank()) {
                return vis.getNombre();
            }
            // Buscar en pases de invitado por placa
            var pase = paseInvitadoRepo.findAll().stream()
                    .filter(p -> e.getPlacaActual().equals(p.getMatricula())
                            && p.getNombreInvitado() != null && !p.getNombreInvitado().isBlank())
                    .findFirst().orElse(null);
            if (pase != null) {
                return pase.getNombreInvitado();
            }
            var vehiculo = vehiculos.stream()
                    .filter(v -> e.getIdVehiculoActual() != null && e.getIdVehiculoActual().equals(v.getIdVehiculo()))
                    .findFirst().orElse(null);
            if (vehiculo != null && vehiculo.getPropietarioNombre() != null) {
                return vehiculo.getPropietarioNombre();
            }
        }
        return null;
    }

    private void enriquecer(Estacionamiento e, List<Vehiculo> vehiculos,
                            java.util.Map<Integer, PropietarioPlaza> propMap,
                            java.util.Map<Integer, PrestamoPlaza> prestMap) {
        PropietarioPlaza prop = propMap.get(e.getIdEstacionamiento());

        PrestamoPlaza prestamoActivo = null;
        PrestamoPlaza prestamoCualquiera = null;
        PrestamoPlaza p = prestMap.get(e.getIdEstacionamiento());
        if (p != null) {
            if ("ACTIVO".equals(p.getEstado())
                    && p.getFechaInicio() != null && p.getFechaInicio().isBefore(java.time.LocalDateTime.now())
                    && p.getFechaFin() != null && p.getFechaFin().isAfter(java.time.LocalDateTime.now())) {
                prestamoActivo = p;
            }
            prestamoCualquiera = p;
        }

        if (prop != null) {
            e.setPropietarioId(prop.getIdPropietario());
            e.setPropietarioNombre(prop.getNombreUsuario());
        }

        boolean ocupada = e.getIdVehiculoActual() != null;

        if (ocupada && prestamoActivo != null) {
            e.setOcupanteNombre(prestamoActivo.getNombreUsuarioAutorizado());
            e.setTipoUso("PRESTAMO");
            e.setPrestamoId(prestamoActivo.getIdPrestamo());
        } else if (ocupada && prestamoCualquiera != null && !"ACTIVO".equals(prestamoCualquiera.getEstado())) {
            e.setOcupanteNombre(prestamoCualquiera.getNombreUsuarioAutorizado());
            e.setTipoUso("PRESTAMO");
            e.setPrestamoId(prestamoCualquiera.getIdPrestamo());
            e.setPrestamoExpirado(true);
        } else if (ocupada && prop != null) {
            boolean vehiculoEsDelPropietario = vehiculos.stream()
                    .anyMatch(v -> e.getIdVehiculoActual() != null
                            && e.getIdVehiculoActual().equals(v.getIdVehiculo())
                            && prop.getIdUsuario().equals(v.getIdUsuarioPropietario()));
            if (vehiculoEsDelPropietario) {
                e.setOcupanteNombre(prop.getNombreUsuario());
                e.setTipoUso("PROPIO");
            } else {
                e.setTipoUso("VISITANTE");
                String nom = buscarNombreOcupante(e, vehiculos);
                if (nom != null) e.setOcupanteNombre(nom);
            }
        } else if (ocupada) {
            e.setTipoUso("VISITANTE");
            String nom = buscarNombreOcupante(e, vehiculos);
            if (nom != null) e.setOcupanteNombre(nom);
        }
    }
}