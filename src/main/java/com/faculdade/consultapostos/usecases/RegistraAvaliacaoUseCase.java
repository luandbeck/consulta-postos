package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.RegistraAvaliacaoDTO;
import com.faculdade.consultapostos.entities.Avaliacao;
import com.faculdade.consultapostos.entities.AvaliacaoHistorico;
import com.faculdade.consultapostos.entities.Posto;
import com.faculdade.consultapostos.repositories.AvaliacaoHistoricoRepository;
import com.faculdade.consultapostos.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistraAvaliacaoUseCase {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoHistoricoRepository avaliacaoHistoricoRepository;

    public void execute(final RegistraAvaliacaoDTO dto) {

        final Posto posto = Posto.builder()
                .id(dto.getPostoId()).build();

        final Avaliacao avaliacao = Avaliacao.builder()
                .posto(posto)
                .media(dto.getNota().doubleValue())
                .build();

        final AvaliacaoHistorico avaliacaoHistorico = AvaliacaoHistorico.builder()
                .posto(posto)
                .nota(dto.getNota())
                .dataHoraInclusao(LocalDateTime.now())
                .build();

        this.avaliacaoRepository.save(avaliacao);
        this.avaliacaoHistoricoRepository.save(avaliacaoHistorico);
    }
}
