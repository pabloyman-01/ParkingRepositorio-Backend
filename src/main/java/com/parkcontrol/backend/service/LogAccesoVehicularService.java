package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.LogAccesoVehicularRequest;
import com.parkcontrol.backend.model.LogAccesoVehicular;
import com.parkcontrol.backend.provider.api.LogAccesoVehicularApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogAccesoVehicularService {
    private final LogAccesoVehicularApiProvider apiProvider;

    public List<LogAccesoVehicular> findAll() {
        return apiProvider.findAll();
    }

    public LogAccesoVehicular findById(Integer id) {
        return apiProvider.findById(id);
    }

    public LogAccesoVehicular create(LogAccesoVehicularRequest request) {
        return apiProvider.create(request);
    }

    public LogAccesoVehicular update(Integer id, LogAccesoVehicularRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
