package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Posto;

import java.util.List;

public interface PostoRepository {

    void insertPosto(Posto posto);

    List<Posto> findAll();

}
