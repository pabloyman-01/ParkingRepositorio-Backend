package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.PaseInvitadoRequest;
import com.parkcontrol.backend.model.PaseInvitado;
import com.parkcontrol.backend.client.PaseInvitadoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaseInvitadoApiProvider {
    private final PaseInvitadoApiClient client;

    public List<PaseInvitado> findAll() {
        return client.findAll();
    }

    public PaseInvitado findById(Integer id) {
        return client.findById(id);
    }

    public PaseInvitado create(PaseInvitadoRequest request) {
        return client.create(request);
    }

    public PaseInvitado update(Integer id, PaseInvitadoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
