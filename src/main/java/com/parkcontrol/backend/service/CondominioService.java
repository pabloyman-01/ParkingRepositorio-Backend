package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.CondominioRequest;
import com.parkcontrol.backend.model.Condominio;
import com.parkcontrol.backend.provider.api.CondominioApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CondominioService {
    private final CondominioApiProvider apiProvider;

    public List<Condominio> findAll() {
        return apiProvider.findAll();
    }

    public Condominio findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Condominio create(CondominioRequest request) {
        return apiProvider.create(request);
    }

    public Condominio update(Integer id, CondominioRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
