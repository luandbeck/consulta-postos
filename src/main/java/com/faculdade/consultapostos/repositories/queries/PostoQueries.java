package com.faculdade.consultapostos.repositories.queries;

public class PostoQueries {

    private PostoQueries() {
    }

    public static final String INSERT_POSTO = "INSERT INTO posto(bairro, cep, cidade, endereco, estado, latitude, " +
            "longitude, nome, numero_endereco) VALUES (:bairro, :cep, :cidade, :endereco, :estado, :latitude, " +
            ":longitude, :nome, :numero_endereco)";

    public static final String SELECT_POSTO = "SELECT id, bairro, cep, cidade, endereco, estado, latitude, longitude," +
            " nome, numero_endereco FROM posto";


}
