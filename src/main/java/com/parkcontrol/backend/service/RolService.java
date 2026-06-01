package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.RolRequest;
import com.parkcontrol.backend.model.Rol;
import com.parkcontrol.backend.provider.api.RolApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolApiProvider apiProvider;

    public List<Rol> findAll() {
        return apiProvider.findAll();
    }

    public Rol findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Rol create(RolRequest request) {
        return apiProvider.create(request);
    }

    public Rol update(Integer id, RolRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
