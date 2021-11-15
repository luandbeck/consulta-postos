package com.faculdade.consultapostos.repositories.queries;

public class PrecoCombustivelQueries {

    private PrecoCombustivelQueries() {
    }

    public static final String INSERT_PRECO = " INSERT INTO preco_combustivel (id_posto, id_combustivel, valor, " +
            "dh_inclusao)  VALUES (:id_posto, :id_combustivel, :valor, :dh_inclusao) ";

    public static final String UPDATE_PRECO = " UPDATE preco_combustivel SET valor = :valor, dh_inclusao = " +
            ":dh_inclusao WHERE id_posto = :id_posto AND id_combustivel = :id_combustivel ";
}
