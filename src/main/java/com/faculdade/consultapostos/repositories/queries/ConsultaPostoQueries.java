package com.faculdade.consultapostos.repositories.queries;

public class ConsultaPostoQueries {

    private ConsultaPostoQueries() {
    }

    public static final String SELECT_POSTO =
            " SELECT " +
                    " p.id, " +
                    " p.nome, " +
                    " p.bairro, " +
                    " p.cep, " +
                    " p.cidade, " +
                    " p.endereco, " +
                    " p.numero_endereco, " +
                    " p.estado, " +
                    " p.latitude, " +
                    " p.longitude, " +
                    " a.media, " +
                    " a.quantidade_avaliacao, " +
                    " c.id_combustivel, " +
                    " d.descricao, " +
                    " c.valor " +
                    " FROM posto p " +
                    " LEFT JOIN avaliacao a " +
                    " ON p.id = a.id_posto " +
                    " LEFT JOIN preco_combustivel c " +
                    " ON p.id = c.id_posto " +
                    " LEFT JOIN combustivel d " +
                    " ON c.id_combustivel = d.id " +
                    " ORDER BY p.id asc ";

    public static final String SELECT_POSTO_POR_CIDADE_COMBUSTIVEL =
            " SELECT " +
                    " p.id, " +
                    " p.nome, " +
                    " p.bairro, " +
                    " p.cep, " +
                    " p.cidade, " +
                    " p.endereco, " +
                    " p.numero_endereco, " +
                    " p.estado, " +
                    " p.latitude, " +
                    " p.longitude, " +
                    " a.media, " +
                    " a.quantidade_avaliacao, " +
                    " c.id_combustivel, " +
                    " d.descricao, " +
                    " c.valor " +
                    " FROM posto p " +
                    " LEFT JOIN avaliacao a " +
                    " ON p.id = a.id_posto " +
                    " LEFT JOIN preco_combustivel c " +
                    " ON p.id = c.id_posto " +
                    " LEFT JOIN combustivel d " +
                    " ON c.id_combustivel = d.id " +
                    " WHERE p.cidade = :cidade " +
                    " AND c.id_combustivel = :id_combustivel " +
                    " ORDER BY p.id asc ";
}
