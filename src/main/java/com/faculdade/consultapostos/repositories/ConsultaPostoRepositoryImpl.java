package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.PostoCompleto;
import com.faculdade.consultapostos.repositories.mappers.PostoCompletoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.faculdade.consultapostos.repositories.queries.ConsultaPostoQueries.SELECT_POSTO;
import static com.faculdade.consultapostos.repositories.queries.ConsultaPostoQueries.SELECT_POSTO_POR_CIDADE_COMBUSTIVEL;

@Repository
public class ConsultaPostoRepositoryImpl implements ConsultaPostoRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public List<PostoCompleto> findAll() {
        return template.query(SELECT_POSTO, new PostoCompletoRowMapper());
    }

    @Override
    public List<PostoCompleto> findByCidadeAndCombustivel(final String cidade, final Long combustivelId) {
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("cidade", cidade)
                .addValue("id_combustivel", combustivelId);

        return template.query(SELECT_POSTO_POR_CIDADE_COMBUSTIVEL, param, new PostoCompletoRowMapper());
    }
}
