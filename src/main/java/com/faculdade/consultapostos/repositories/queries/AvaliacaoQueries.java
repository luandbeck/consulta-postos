package com.faculdade.consultapostos.repositories.queries;

public class AvaliacaoQueries {

    private AvaliacaoQueries() {
    }

    public static final String SELECT_AVALIACAO = " SELECT id_posto, media, quantidade_avaliacao FROM avaliacao " +
            " WHERE id_posto = :id_posto ";

    public static final String INSERT_AVALIACAO = " INSERT INTO avaliacao (id_posto, media, quantidade_avaliacao) " +
            " VALUES (:id_posto, :media, :quantidade_avaliacao) ";

    public static final String UPDATE_AVALIACAO = " UPDATE avaliacao SET media = :media, quantidade_avaliacao = " +
            ":quantidade_avaliacao WHERE id_posto = :id_posto";
}
