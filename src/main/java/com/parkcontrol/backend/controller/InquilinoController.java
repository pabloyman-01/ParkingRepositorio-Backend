package com.parkcontrol.backend.controller;

import com.parkcontrol.backend.common.response.ApiResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquilinos")
public class InquilinoController {

    private final JdbcTemplate jdbc;

    public InquilinoController(JdbcTemplate neonJdbcTemplate) {
        this.jdbc = neonJdbcTemplate;
    }

    private final RowMapper<InquilinoDTO> mapper = (rs, rowNum) -> new InquilinoDTO(
        rs.getLong("id_usuario"),
        rs.getString("nombre_completo"),
        rs.getString("unidad"),
        rs.getLong("id_apartamento"),
        rs.getLong("id_condominio"),
        rs.getString("condominio_nombre")
    );

    @GetMapping
    public ApiResponse<List<InquilinoDTO>> getInquilinos(
            @RequestParam(required = false) Long condominioId) {

        String sql = """
            SELECT u.id_usuario,
                   CONCAT(u.nombres, ' ', u.apellidos) AS nombre_completo,
                   a.numero AS unidad,
                   u.id_apartamento,
                   COALESCE(u.id_condominio, c.id_condominio) AS id_condominio,
                   COALESCE(c.nombre, cc.nombre) AS condominio_nombre
            FROM usuario u
            LEFT JOIN apartamento a ON u.id_apartamento = a.id_apartamento
            LEFT JOIN condominio c ON u.id_condominio = c.id_condominio
            LEFT JOIN piso p ON a.id_piso = p.id_piso
            LEFT JOIN torre t ON p.id_torre = t.id_torre
            LEFT JOIN condominio cc ON t.id_condominio = cc.id_condominio
        """;

        if (condominioId != null) {
            sql += " WHERE COALESCE(u.id_condominio, c.id_condominio) = ?";
            return ApiResponse.ok(jdbc.query(sql, mapper, condominioId));
        }

        return ApiResponse.ok(jdbc.query(sql, mapper));
    }

    public record InquilinoDTO(
        Long idUsuario,
        String nombreCompleto,
        String unidad,
        Long idApartamento,
        Long idCondominio,
        String condominioNombre
    ) {}
}