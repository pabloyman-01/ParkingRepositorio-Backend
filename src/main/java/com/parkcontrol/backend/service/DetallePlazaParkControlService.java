package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.DetallePlazaParkControlRequest;
import com.parkcontrol.backend.model.DetallePlazaParkControl;
import com.parkcontrol.backend.provider.api.DetallePlazaParkControlApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePlazaParkControlService {
    private final DetallePlazaParkControlApiProvider apiProvider;

    public List<DetallePlazaParkControl> findAll() {
        return apiProvider.findAll();
    }

    public DetallePlazaParkControl findById(Integer id) {
        return apiProvider.findById(id);
    }

    public DetallePlazaParkControl create(DetallePlazaParkControlRequest request) {
        return apiProvider.create(request);
    }

    public DetallePlazaParkControl update(Integer id, DetallePlazaParkControlRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
