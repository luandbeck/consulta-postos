package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Avaliacao;
import com.faculdade.consultapostos.repositories.mappers.AvaliacaoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.faculdade.consultapostos.repositories.queries.AvaliacaoQueries.*;

@Repository
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoRepositoryImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void save(final Avaliacao avaliacao) {
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_posto", avaliacao.getPostoId())
                .addValue("media", avaliacao.getMedia())
                .addValue("quantidade_avaliacao", avaliacao.getQuantidadeAvaliacao());

        final int result = template.update(UPDATE_AVALIACAO, param);

        if (0 == result) {
            template.update(INSERT_AVALIACAO, param);
        }
    }

    @Override
    public Avaliacao findById(final Long postoId) {
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_posto", postoId);

        try {
            return template.queryForObject(SELECT_AVALIACAO, param, new AvaliacaoRowMapper());
        } catch (final EmptyResultDataAccessException e) {
            LOGGER.info("Nenhuma avaliação encontrada");

            return Avaliacao.builder()
                    .postoId(postoId)
                    .media((double) 0)
                    .quantidadeAvaliacao(0)
                    .build();
        }
    }

}
