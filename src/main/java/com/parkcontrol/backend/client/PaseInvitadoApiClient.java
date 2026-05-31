package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.PaseInvitadoResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.model.PaseInvitado;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class PaseInvitadoApiClient {
    private final RestClient restClient;

    public PaseInvitadoApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<PaseInvitado> findAll() {
        List<PaseInvitadoResponseDTO> dtos = restClient.get()
            .uri("/api/pases-invitado")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PaseInvitadoResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toPaseInvitado)
            .toList();
    }

    public PaseInvitado findById(Integer id) {
        PaseInvitadoResponseDTO dto = restClient.get()
            .uri("/api/pases-invitado/{id}", id)
            .retrieve()
            .body(PaseInvitadoResponseDTO.class);
        return MappingUtil.toPaseInvitado(dto);
    }
}
