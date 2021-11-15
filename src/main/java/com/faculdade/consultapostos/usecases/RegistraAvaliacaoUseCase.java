package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.request.RegistraAvaliacaoDTO;
import com.faculdade.consultapostos.entities.Avaliacao;
import com.faculdade.consultapostos.entities.AvaliacaoHistorico;
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
        final Avaliacao avaliacao = this.avaliacaoRepository.findById(dto.getPostoId());

        final AvaliacaoHistorico avaliacaoHistorico = AvaliacaoHistorico.builder()
                .postoId(dto.getPostoId())
                .nota(dto.getNota())
                .dataHoraInclusao(LocalDateTime.now())
                .build();

        this.avaliacaoRepository.save(this.calcularMedia(avaliacao, dto.getNota()));
        this.avaliacaoHistoricoRepository.save(avaliacaoHistorico);
    }

    private Avaliacao calcularMedia(final Avaliacao avaliacao, final Integer nota) {
        final Double mediaAtual = avaliacao.getMedia();
        final Integer quantidadeAtual = avaliacao.getQuantidadeAvaliacao();
        final int quantidadeNova = quantidadeAtual + 1;

        final double mediaNova = ((quantidadeAtual * mediaAtual) + nota) / quantidadeNova;

        return Avaliacao.builder()
                .postoId(avaliacao.getPostoId())
                .media(mediaNova)
                .quantidadeAvaliacao(quantidadeNova)
                .build();
    }
}