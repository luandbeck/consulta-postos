package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.request.RegistraPrecoDTO;
import com.faculdade.consultapostos.entities.PrecoCombustivel;
import com.faculdade.consultapostos.repositories.PrecoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistraPrecoUseCase {

    @Autowired
    private PrecoCombustivelRepository precoCombustivelRepository;

    public void execute(final RegistraPrecoDTO dto) {
        final PrecoCombustivel preco = PrecoCombustivel.builder()
                .postoId(dto.getPostoId())
                .combustivelId(dto.getCombustivelId())
                .valor(dto.getPreco())
                .dataHoraInclusao(LocalDateTime.now())
                .build();

        this.precoCombustivelRepository.save(preco);
    }
}