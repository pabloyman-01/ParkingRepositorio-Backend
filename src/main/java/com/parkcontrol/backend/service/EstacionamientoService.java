package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.EstacionamientoRequest;
import com.parkcontrol.backend.model.Estacionamiento;
import com.parkcontrol.backend.model.PrestamoPlaza;
import com.parkcontrol.backend.model.PropietarioPlaza;
import com.parkcontrol.backend.provider.api.EstacionamientoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamientoService {
    private final EstacionamientoApiProvider apiProvider;
    private final PropietarioPlazaStore propietarioStore;
    private final PrestamoPlazaStore prestamoStore;

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
        PrestamoPlaza prestamo = prestamoStore.findByIdEstacionamientoActivo(e.getIdEstacionamiento());

        if (prop != null) {
            e.setPropietarioId(prop.getIdPropietario());
            e.setPropietarioNombre(prop.getNombreUsuario());
        }

        boolean ocupada = e.getIdVehiculoActual() != null;

        if (ocupada && prestamo != null) {
            e.setOcupanteNombre(prestamo.getNombreUsuarioAutorizado());
            e.setTipoUso("PRESTAMO");
            e.setPrestamoId(prestamo.getIdPrestamo());
        } else if (ocupada && prop != null) {
            e.setOcupanteNombre(prop.getNombreUsuario());
            e.setTipoUso("PROPIO");
        } else if (ocupada) {
            e.setTipoUso("VISITANTE");
        }
    }
}