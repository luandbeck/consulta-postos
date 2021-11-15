package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.request.RegistraCombustivelDTO;
import com.faculdade.consultapostos.entities.Combustivel;
import com.faculdade.consultapostos.repositories.CombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistraCombustivelUseCase {

    @Autowired
    private CombustivelRepository repository;

    public void execute(final RegistraCombustivelDTO dto) {
        final Combustivel combustivel = Combustivel.builder()
                .descricao(dto.getDescricao())
                .build();

        this.repository.save(combustivel);
    }
}