package com.faculdade.consultapostos.repositories.mappers;

import com.faculdade.consultapostos.entities.PostoCompleto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostoCompletoRowMapper implements RowMapper<PostoCompleto> {

    @Override
    public PostoCompleto mapRow(final ResultSet rs, final int i) throws SQLException {
        final PostoCompleto posto = new PostoCompleto();
        posto.setId(rs.getLong("id"));
        posto.setNome(rs.getString("nome"));
        posto.setEndereco(rs.getString("endereco"));
        posto.setNumeroEndereco(rs.getLong("numero_endereco"));
        posto.setBairro(rs.getString("bairro"));
        posto.setCidade(rs.getString("cidade"));
        posto.setEstado(rs.getString("estado"));
        posto.setLatitude(rs.getString("latitude"));
        posto.setLongitude(rs.getString("longitude"));
        posto.setCep(rs.getString("cep"));
        posto.setMediaAvaliacao(rs.getDouble("media"));
        posto.setQuantidadeAvaliacao(rs.getInt("quantidade_avaliacao"));
        posto.setCombustivelId(rs.getLong("id_combustivel"));
        posto.setCombustivelDescricao(rs.getString("descricao"));
        posto.setValorCombustivel(rs.getDouble("valor"));

        return posto;
    }
}
