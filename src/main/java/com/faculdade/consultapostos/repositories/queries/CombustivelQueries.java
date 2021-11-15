package com.faculdade.consultapostos.repositories.queries;

public class CombustivelQueries {

    private CombustivelQueries() {
    }

    public static final String INSERT_COMBUSTIVEL = "INSERT INTO combustivel(descricao) VALUES (:descricao)";

    public static final String SELECT_COMBUSTIVEL = "SELECT id, descricao FROM combustivel";


}
