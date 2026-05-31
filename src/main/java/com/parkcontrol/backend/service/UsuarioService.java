package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.UsuarioRequest;
import com.parkcontrol.backend.model.Usuario;
import com.parkcontrol.backend.provider.api.UsuarioApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioApiProvider apiProvider;

    public List<Usuario> findAll() {
        return apiProvider.findAll();
    }

    public Usuario findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Usuario create(UsuarioRequest request) {
        return apiProvider.create(request);
    }

    public Usuario update(Integer id, UsuarioRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
