package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.AvaliacaoHistorico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.faculdade.consultapostos.repositories.queries.AvaliacaoHistoricoQueries.INSERT_AVALIACAO;

@Repository
public class AvaliacaoHistoricoRepositoryImpl implements AvaliacaoHistoricoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoHistoricoRepositoryImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void save(final AvaliacaoHistorico avaliacaoHistorico) {
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_posto", avaliacaoHistorico.getPostoId())
                .addValue("nota", avaliacaoHistorico.getNota())
                .addValue("dh_inclusao", avaliacaoHistorico.getDataHoraInclusao());

        template.update(INSERT_AVALIACAO, param);
    }
}
