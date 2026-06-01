package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.TorreRequest;
import com.parkcontrol.backend.model.Torre;
import com.parkcontrol.backend.provider.api.TorreApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorreService {
    private final TorreApiProvider apiProvider;

    public List<Torre> findAll() {
        return apiProvider.findAll();
    }

    public Torre findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Torre create(TorreRequest request) {
        return apiProvider.create(request);
    }

    public Torre update(Integer id, TorreRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
