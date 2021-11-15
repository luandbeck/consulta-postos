package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.request.CriaPostoDTO;
import com.faculdade.consultapostos.entities.Posto;
import com.faculdade.consultapostos.providers.NominatimProvider;
import com.faculdade.consultapostos.providers.dtos.NominatimResponse;
import com.faculdade.consultapostos.repositories.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriaPostoUseCase {

    @Autowired
    private PostoRepository repository;

    @Autowired
    private NominatimProvider nominatimProvider;

    public void execute(final CriaPostoDTO dto) {
        final NominatimResponse coordenadas = nominatimProvider.getCoordenadas(dto.getEndereco(),
                dto.getNumeroEndereco());

        final Posto posto = Posto.builder()
                .nome(dto.getNome())
                .endereco(dto.getEndereco())
                .numeroEndereco(dto.getNumeroEndereco())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .latitude(coordenadas.getLat())
                .longitude(coordenadas.getLon())
                .build();

        this.repository.insertPosto(posto);
    }
}
