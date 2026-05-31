package com.parkcontrol.backend.client;

import com.parkcontrol.backend.client.dto.UsuarioResponseDTO;
import com.parkcontrol.backend.config.ApiProperties;
import com.parkcontrol.backend.dto.UsuarioRequest;
import com.parkcontrol.backend.model.Usuario;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class UsuarioApiClient {
    private final RestClient restClient;

    public UsuarioApiClient(ApiProperties apiProperties) {
        this.restClient = RestClient.builder()
            .baseUrl(apiProperties.getBaseUrl())
            .build();
    }

    public List<Usuario> findAll() {
        List<UsuarioResponseDTO> dtos = restClient.get()
            .uri("/api/usuarios")
            .retrieve()
            .body(new ParameterizedTypeReference<List<UsuarioResponseDTO>>() {});
        return dtos.stream()
            .map(MappingUtil::toUsuario)
            .toList();
    }

    public Usuario findById(Integer id) {
        UsuarioResponseDTO dto = restClient.get()
            .uri("/api/usuarios/{id}", id)
            .retrieve()
            .body(UsuarioResponseDTO.class);
        return MappingUtil.toUsuario(dto);
    }

    public Usuario create(UsuarioRequest request) {
        Map<String, Object> body = Map.of(
            "nombres", request.nombreCompleto(),
            "apellidos", "",
            "email", request.email(),
            "telefono", "",
            "password", request.passwordHash(),
            "tipoOcupante", "PROPIETARIO",
            "estado", "ACTIVO",
            "rolId", request.idRol(),
            "apartamentoId", request.idCondominio()
        );
        UsuarioResponseDTO dto = restClient.post()
            .uri("/api/usuarios/create")
            .body(body)
            .retrieve()
            .body(UsuarioResponseDTO.class);
        return MappingUtil.toUsuario(dto);
    }

    public Usuario update(Integer id, UsuarioRequest request) {
        Map<String, Object> body = Map.of(
            "nombres", request.nombreCompleto(),
            "apellidos", "",
            "email", request.email(),
            "telefono", "",
            "password", request.passwordHash(),
            "tipoOcupante", "PROPIETARIO",
            "estado", "ACTIVO",
            "rolId", request.idRol(),
            "apartamentoId", request.idCondominio()
        );
        UsuarioResponseDTO dto = restClient.put()
            .uri("/api/usuarios/{id}/update", id)
            .body(body)
            .retrieve()
            .body(UsuarioResponseDTO.class);
        return MappingUtil.toUsuario(dto);
    }

    public void delete(Integer id) {
        restClient.delete()
            .uri("/api/usuarios/{id}/delete", id)
            .retrieve()
            .toBodilessEntity();
    }
}
