package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.UsuarioRequest;
import com.parkcontrol.backend.model.Usuario;
import com.parkcontrol.backend.client.UsuarioApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioApiProvider {
    private final UsuarioApiClient client;

    public List<Usuario> findAll() {
        return client.findAll();
    }

    public Usuario findById(Integer id) {
        return client.findById(id);
    }

    public Usuario create(UsuarioRequest request) {
        return client.create(request);
    }

    public Usuario update(Integer id, UsuarioRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
