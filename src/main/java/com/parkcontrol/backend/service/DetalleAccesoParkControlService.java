package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.DetalleAccesoParkControlRequest;
import com.parkcontrol.backend.model.DetalleAccesoParkControl;
import com.parkcontrol.backend.provider.api.DetalleAccesoParkControlApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleAccesoParkControlService {
    private final DetalleAccesoParkControlApiProvider apiProvider;

    public List<DetalleAccesoParkControl> findAll() {
        return apiProvider.findAll();
    }

    public DetalleAccesoParkControl findById(Integer id) {
        return apiProvider.findById(id);
    }

    public DetalleAccesoParkControl create(DetalleAccesoParkControlRequest request) {
        return apiProvider.create(request);
    }

    public DetalleAccesoParkControl update(Integer id, DetalleAccesoParkControlRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
