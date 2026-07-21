package com.parkcontrol.backend.service;

import com.parkcontrol.backend.common.util.PaseCodeGenerator;
import com.parkcontrol.backend.dto.PaseInvitadoRequest;
import com.parkcontrol.backend.model.PaseInvitado;
import com.parkcontrol.backend.provider.api.PaseInvitadoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaseInvitadoService {
    private final PaseInvitadoApiProvider apiProvider;
    private final PaseInvitadoStore store;

    public List<PaseInvitado> findAll() {
        List<PaseInvitado> list = apiProvider.findAll();
        for (PaseInvitado p : list) {
            PaseInvitadoStore.LocalPase local = store.get(p.getIdPase());
            if (local != null) {
                if (p.getMatricula() == null || p.getMatricula().isBlank()) {
                    p.setMatricula(local.matricula());
                }
                if (p.getNombreInvitado() == null || p.getNombreInvitado().isBlank()) {
                    p.setNombreInvitado(local.nombreInvitado());
                }
                if (p.getIdApartamento() == null) {
                    p.setIdApartamento(local.idApartamento());
                }
            }
        }
        return list;
    }

    public PaseInvitado findById(Integer id) {
        PaseInvitado p = apiProvider.findById(id);
        PaseInvitadoStore.LocalPase local = store.get(id);
        if (local != null && p != null) {
            if (p.getMatricula() == null || p.getMatricula().isBlank()) {
                p.setMatricula(local.matricula());
            }
            if (p.getNombreInvitado() == null || p.getNombreInvitado().isBlank()) {
                p.setNombreInvitado(local.nombreInvitado());
            }
            if (p.getIdApartamento() == null) {
                p.setIdApartamento(local.idApartamento());
            }
        }
        return p;
    }

    public PaseInvitado create(PaseInvitadoRequest request) {
        String code = (request.codigoPase() == null || request.codigoPase().isBlank())
                ? PaseCodeGenerator.generate()
                : request.codigoPase();
        PaseInvitadoRequest enriched = new PaseInvitadoRequest(
                code,
                request.matricula(),
                request.idApartamento(),
                request.idUsuarioEmisor(),
                request.fechaInicio(),
                request.fechaFin(),
                request.estado(),
                request.nombreInvitado()
        );
        PaseInvitado result = apiProvider.create(enriched);
        // CondoSaaS no devuelve matricula ni idApartamento, los guardamos localmente
        if (result != null && result.getIdPase() != null) {
            store.save(result.getIdPase(), request.matricula(), request.nombreInvitado(), request.idApartamento());
            result.setMatricula(request.matricula());
            result.setNombreInvitado(request.nombreInvitado() != null ? request.nombreInvitado() : "");
            result.setIdApartamento(request.idApartamento());
        }
        return result;
    }

    public PaseInvitado update(Integer id, PaseInvitadoRequest request) {
        PaseInvitado result = apiProvider.update(id, request);
        if (result != null) {
            store.save(id, request.matricula(), request.nombreInvitado(), request.idApartamento());
            result.setMatricula(request.matricula());
            result.setNombreInvitado(request.nombreInvitado() != null ? request.nombreInvitado() : "");
            result.setIdApartamento(request.idApartamento());
        }
        return result;
    }

    public void delete(Integer id) {
        store.remove(id);
        apiProvider.delete(id);
    }
}
