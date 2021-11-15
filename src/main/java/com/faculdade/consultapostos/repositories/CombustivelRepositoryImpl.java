package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Combustivel;
import com.faculdade.consultapostos.repositories.mappers.CombustivelRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.faculdade.consultapostos.repositories.queries.CombustivelQueries.INSERT_COMBUSTIVEL;
import static com.faculdade.consultapostos.repositories.queries.CombustivelQueries.SELECT_COMBUSTIVEL;

@Repository
public class CombustivelRepositoryImpl implements CombustivelRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public List<Combustivel> findAll() {

        return template.query(SELECT_COMBUSTIVEL, new CombustivelRowMapper());
    }

    @Override
    public void save(final Combustivel combustivel) {

        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("descricao", combustivel.getDescricao());

        template.update(INSERT_COMBUSTIVEL, param);
    }
}
