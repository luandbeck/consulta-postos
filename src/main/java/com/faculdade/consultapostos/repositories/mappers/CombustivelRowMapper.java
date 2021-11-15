package com.faculdade.consultapostos.repositories.mappers;

import com.faculdade.consultapostos.entities.Combustivel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CombustivelRowMapper implements RowMapper<Combustivel> {

    @Override
    public Combustivel mapRow(final ResultSet rs, final int i) throws SQLException {
        final Combustivel combustivel = new Combustivel();
        combustivel.setId(rs.getLong("id"));
        combustivel.setDescricao(rs.getString("descricao"));

        return combustivel;
    }
}
