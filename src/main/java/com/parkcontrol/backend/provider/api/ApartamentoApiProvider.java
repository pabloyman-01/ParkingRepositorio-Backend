package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.ApartamentoRequest;
import com.parkcontrol.backend.model.Apartamento;
import com.parkcontrol.backend.client.ApartamentoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApartamentoApiProvider {
    private final ApartamentoApiClient client;

    public List<Apartamento> findAll() {
        return client.findAll();
    }

    public Apartamento findById(Integer id) {
        return client.findById(id);
    }

    public Apartamento create(ApartamentoRequest request) {
        return client.create(request);
    }

    public Apartamento update(Integer id, ApartamentoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
