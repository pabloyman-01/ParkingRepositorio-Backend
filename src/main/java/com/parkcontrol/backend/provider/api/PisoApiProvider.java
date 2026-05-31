package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.PisoRequest;
import com.parkcontrol.backend.model.Piso;
import com.parkcontrol.backend.client.PisoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PisoApiProvider {
    private final PisoApiClient client;

    public List<Piso> findAll() {
        return client.findAll();
    }

    public Piso findById(Integer id) {
        return client.findById(id);
    }

    public Piso create(PisoRequest request) {
        return client.create(request);
    }

    public Piso update(Integer id, PisoRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
