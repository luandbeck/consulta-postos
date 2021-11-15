package com.faculdade.consultapostos.repositories.mappers;

import com.faculdade.consultapostos.entities.Avaliacao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliacaoRowMapper implements RowMapper<Avaliacao> {

    @Override
    public Avaliacao mapRow(final ResultSet rs, final int i) throws SQLException {
        final Avaliacao avaliacao = new Avaliacao();
        avaliacao.setPostoId(rs.getLong("id_posto"));
        avaliacao.setMedia(rs.getDouble("media"));
        avaliacao.setQuantidadeAvaliacao(rs.getInt("quantidade_avaliacao"));

        return avaliacao;
    }
}
