package com.faculdade.consultapostos.repositories.mappers;

import com.faculdade.consultapostos.entities.Posto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostoRowMapper implements RowMapper<Posto> {

    @Override
    public Posto mapRow(final ResultSet rs, final int i) throws SQLException {
        final Posto posto = new Posto();
        posto.setId(rs.getLong("id"));
        posto.setNome(rs.getString("nome"));
        posto.setEndereco(rs.getString("endereco"));
        posto.setNumeroEndereco(rs.getLong("numero_endereco"));
        posto.setBairro(rs.getString("bairro"));
        posto.setCidade(rs.getString("cidade"));
        posto.setEstado(rs.getString("estado"));
        posto.setCep(rs.getString("cep"));
        posto.setLatitude(rs.getString("latitude"));
        posto.setLongitude(rs.getString("longitude"));

        return posto;
    }
}
