package com.parkcontrol.backend.provider.api;

import com.parkcontrol.backend.dto.TorreRequest;
import com.parkcontrol.backend.model.Torre;
import com.parkcontrol.backend.client.TorreApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TorreApiProvider {
    private final TorreApiClient client;

    public List<Torre> findAll() {
        return client.findAll();
    }

    public Torre findById(Integer id) {
        return client.findById(id);
    }

    public Torre create(TorreRequest request) {
        return client.create(request);
    }

    public Torre update(Integer id, TorreRequest request) {
        return client.update(id, request);
    }

    public void delete(Integer id) {
        client.delete(id);
    }
}
