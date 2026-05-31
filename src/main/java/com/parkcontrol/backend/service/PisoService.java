package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.PisoRequest;
import com.parkcontrol.backend.model.Piso;
import com.parkcontrol.backend.provider.api.PisoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PisoService {
    private final PisoApiProvider apiProvider;

    public List<Piso> findAll() {
        return apiProvider.findAll();
    }

    public Piso findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Piso create(PisoRequest request) {
        return apiProvider.create(request);
    }

    public Piso update(Integer id, PisoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
