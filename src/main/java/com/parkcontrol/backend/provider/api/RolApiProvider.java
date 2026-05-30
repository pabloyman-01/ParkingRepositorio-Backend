package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.RolRequest;
import com.parkcontrol.backend.model.Rol;
import com.parkcontrol.backend.client.RolApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RolApiProvider {
    private final RolApiClient client;

    public List<Rol> findAll() {
        return client.findAll();
    }

    public Rol findById(Integer id) {
        return client.findById(id);
    }

    public Rol create(RolRequest request) {
        return client.create(request);
    }

    public Rol update(Integer id, RolRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
