package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.DetallePlazaParkControlRequest;
import com.parkcontrol.backend.model.DetallePlazaParkControl;
import com.parkcontrol.backend.client.DetallePlazaParkControlApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DetallePlazaParkControlApiProvider {
    private final DetallePlazaParkControlApiClient client;

    public List<DetallePlazaParkControl> findAll() {
        return client.findAll();
    }

    public DetallePlazaParkControl findById(Integer id) {
        return client.findById(id);
    }

    public DetallePlazaParkControl create(DetallePlazaParkControlRequest request) {
        return client.create(request);
    }

    public DetallePlazaParkControl update(Integer id, DetallePlazaParkControlRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
