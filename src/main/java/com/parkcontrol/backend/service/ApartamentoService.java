package com.parkcontrol.backend.service;

import com.parkcontrol.backend.dto.ApartamentoRequest;
import com.parkcontrol.backend.model.Apartamento;
import com.parkcontrol.backend.provider.api.ApartamentoApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartamentoService {
    private final ApartamentoApiProvider apiProvider;

    public List<Apartamento> findAll() {
        return apiProvider.findAll();
    }

    public Apartamento findById(Integer id) {
        return apiProvider.findById(id);
    }

    public Apartamento create(ApartamentoRequest request) {
        return apiProvider.create(request);
    }

    public Apartamento update(Integer id, ApartamentoRequest request) {
        return apiProvider.update(id, request);
    }

    public void delete(Integer id) {
        apiProvider.delete(id);
    }
}
