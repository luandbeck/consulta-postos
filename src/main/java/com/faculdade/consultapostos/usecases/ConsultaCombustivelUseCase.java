package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.entities.Combustivel;
import com.faculdade.consultapostos.repositories.CombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaCombustivelUseCase {

    @Autowired
    private CombustivelRepository repository;

    public List<Combustivel> execute() {
        return repository.findAll();
    }
}