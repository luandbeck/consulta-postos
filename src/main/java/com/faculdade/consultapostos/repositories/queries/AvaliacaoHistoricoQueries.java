package com.faculdade.consultapostos.repositories.queries;

public class AvaliacaoHistoricoQueries {

    private AvaliacaoHistoricoQueries() {
    }

    public static final String INSERT_AVALIACAO = " INSERT INTO avaliacao_historico (id_posto, nota, dh_inclusao) " +
            "VALUES (:id_posto, :nota, :dh_inclusao) ";
}
