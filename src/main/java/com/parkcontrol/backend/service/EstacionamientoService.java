package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import com.parkcontrol.backend.model.PrestamoPlaza;
import com.parkcontrol.backend.model.PropietarioPlaza;
import com.parkcontrol.backend.provider.api.EstacionamientoApiProvider;
import com.parkcontrol.backend.provider.api.VehiculoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamientoService {
    private final EstacionamientoApiProvider apiProvider;
    private final PropietarioPlazaStore propietarioStore;
    private final PrestamoPlazaStore prestamoStore;
    private final VehiculoApiProvider vehiculoProvider;

    public List<Estacionamiento> findAll() {
        List<Estacionamiento> plazas = apiProvider.findAll();
        for (Estacionamiento e : plazas) {
            enriquecer(e);
        }
        return plazas;
    }

    public Estacionamiento findById(Integer id) {
        Estacionamiento e = apiProvider.findById(id);
        if (e != null) enriquecer(e);
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

    private void enriquecer(Estacionamiento e) {
        PropietarioPlaza prop = propietarioStore.findByIdEstacionamiento(e.getIdEstacionamiento());
        PrestamoPlaza prestamoActivo = prestamoStore.findByIdEstacionamientoActivo(e.getIdEstacionamiento());
        PrestamoPlaza prestamoCualquiera = prestamoStore.findByIdEstacionamientoCualquierEstado(e.getIdEstacionamiento());

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
            boolean vehiculoEsDelPropietario = vehiculoProvider.findAll().stream()
                    .anyMatch(v -> e.getIdVehiculoActual() != null
                            && e.getIdVehiculoActual().equals(v.getIdVehiculo())
                            && prop.getIdUsuario().equals(v.getIdUsuarioPropietario()));
            if (vehiculoEsDelPropietario) {
                e.setOcupanteNombre(prop.getNombreUsuario());
                e.setTipoUso("PROPIO");
            } else {
                e.setTipoUso("VISITANTE");
            }
        } else if (ocupada) {
            e.setTipoUso("VISITANTE");
        }
    }
}