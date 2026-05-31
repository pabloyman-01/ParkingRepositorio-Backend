package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.model.LogAccesoVehicular;
import com.parkcontrol.backend.client.LogAccesoVehicularApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LogAccesoVehicularApiProvider {
    private final LogAccesoVehicularApiClient client;

    public List<LogAccesoVehicular> findAll() {
        return client.findAll();
    }

    public LogAccesoVehicular findById(Integer id) {
        return client.findById(id);
    }

    public LogAccesoVehicular create(LogAccesoVehicularRequest request) {
        return client.create(request);
    }

    public LogAccesoVehicular update(Integer id, LogAccesoVehicularRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
