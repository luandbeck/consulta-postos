package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.request.BuscaPostoDTO;
import com.faculdade.consultapostos.dtos.response.ConsultaPostoResponseDTO;
import com.faculdade.consultapostos.entities.PostoCompleto;
import com.faculdade.consultapostos.enums.OrdemBusca;
import com.faculdade.consultapostos.providers.NominatimProvider;
import com.faculdade.consultapostos.providers.dtos.NominatimResponse;
import com.faculdade.consultapostos.repositories.ConsultaPostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscaPostoUseCase {

    @Autowired
    private ConsultaPostoRepository repository;

    @Autowired
    private NominatimProvider nominatimProvider;

    public Collection<ConsultaPostoResponseDTO> execute(final BuscaPostoDTO dto) {
        final List<PostoCompleto> postos = repository.findByCidadeAndCombustivel(dto.getCidade(),
                dto.getCombustivelId());

        final Collection<ConsultaPostoResponseDTO> mergeLines = postos.stream()
                .collect(Collectors.toMap(PostoCompleto::getId, ConsultaPostoResponseDTO::new,
                        ConsultaPostoResponseDTO::merge)).values();

        final NominatimResponse coordenadasLocais = nominatimProvider.getCoordenadas(dto.getEndereco(),
                dto.getNumeroEndereco());

        mergeLines.stream().forEach(posto -> posto.calcularDistancia(coordenadasLocais.getLat(),
                coordenadasLocais.getLon()));

        return this.ordenarLista(mergeLines, dto.getOrdemBusca());
    }

    private Collection<ConsultaPostoResponseDTO> ordenarLista(final Collection<ConsultaPostoResponseDTO> resultado,
                                                              final OrdemBusca ordem) {
        if (OrdemBusca.COMBUSTIVEL.equals(ordem)) {
            return resultado.stream().sorted(Comparator.comparing(o -> o.getCombustiveis().get(0).getValor())).
                    collect(Collectors.toList());
        }

        return resultado.stream().sorted(Comparator.comparing(ConsultaPostoResponseDTO::getDistancia)).collect(Collectors.toList());
    }
}
