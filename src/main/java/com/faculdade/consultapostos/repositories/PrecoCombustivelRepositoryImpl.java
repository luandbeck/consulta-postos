package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.PrecoCombustivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.faculdade.consultapostos.repositories.queries.PrecoCombustivelQueries.INSERT_PRECO;
import static com.faculdade.consultapostos.repositories.queries.PrecoCombustivelQueries.UPDATE_PRECO;

@Repository
public class PrecoCombustivelRepositoryImpl implements PrecoCombustivelRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void save(final PrecoCombustivel posto) {

        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_posto", posto.getPostoId())
                .addValue("id_combustivel", posto.getCombustivelId())
                .addValue("valor", posto.getValor())
                .addValue("dh_inclusao", posto.getDataHoraInclusao());

        final int result = template.update(UPDATE_PRECO, param);

        if (0 == result) {
            template.update(INSERT_PRECO, param);
        }
    }
}
