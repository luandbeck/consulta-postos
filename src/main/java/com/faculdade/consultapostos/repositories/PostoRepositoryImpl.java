package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Posto;
import com.faculdade.consultapostos.repositories.mappers.PostoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.faculdade.consultapostos.repositories.queries.PostoQueries.INSERT_POSTO;
import static com.faculdade.consultapostos.repositories.queries.PostoQueries.SELECT_POSTO;

@Repository
public class PostoRepositoryImpl implements PostoRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void insertPosto(final Posto posto) {

        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("bairro", posto.getBairro())
                .addValue("cep", posto.getCep())
                .addValue("cidade", posto.getCidade())
                .addValue("endereco", posto.getEndereco())
                .addValue("estado", posto.getEstado())
                .addValue("latitude", posto.getLatitude())
                .addValue("longitude", posto.getLongitude())
                .addValue("nome", posto.getNome())
                .addValue("numero_endereco", posto.getNumeroEndereco());

        template.update(INSERT_POSTO, param);
    }

    @Override
    public List<Posto> findAll() {
        return template.query(SELECT_POSTO, new PostoRowMapper());
    }
}
