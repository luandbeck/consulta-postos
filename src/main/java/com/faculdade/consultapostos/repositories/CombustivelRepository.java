package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Combustivel;

import java.util.List;

public interface CombustivelRepository {

    void save(Combustivel combustive);

    List<Combustivel> findAll();

}
