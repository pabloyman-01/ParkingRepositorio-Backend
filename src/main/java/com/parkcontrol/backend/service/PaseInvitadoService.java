package com.parkcontrol.backend.service;

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

    public List<PaseInvitado> findAll() {
        return apiProvider.findAll();
    }

    public PaseInvitado findById(Integer id) {
        return apiProvider.findById(id);
    }

    public PaseInvitado create(PaseInvitadoRequest request) {
        return apiProvider.create(request);
    }

    public PaseInvitado update(Integer id, PaseInvitadoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
