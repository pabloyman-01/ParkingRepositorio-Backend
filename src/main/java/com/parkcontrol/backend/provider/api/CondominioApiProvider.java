package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.CondominioRequest;
import com.parkcontrol.backend.model.Condominio;
import com.parkcontrol.backend.client.CondominioApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CondominioApiProvider {
    private final CondominioApiClient client;

    public List<Condominio> findAll() {
        return client.findAll();
    }

    public Condominio findById(Integer id) {
        return client.findById(id);
    }

    public Condominio create(CondominioRequest request) {
        return client.create(request);
    }

    public Condominio update(Integer id, CondominioRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
