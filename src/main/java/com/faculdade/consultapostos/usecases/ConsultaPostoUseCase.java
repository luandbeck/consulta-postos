package com.faculdade.consultapostos.usecases;

import com.faculdade.consultapostos.dtos.response.ConsultaPostoResponseDTO;
import com.faculdade.consultapostos.entities.PostoCompleto;
import com.faculdade.consultapostos.repositories.ConsultaPostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConsultaPostoUseCase {

    @Autowired
    private ConsultaPostoRepository repository;

    public Collection<ConsultaPostoResponseDTO> execute() {
        final List<PostoCompleto> postos = repository.findAll();

        final Map<Long, ConsultaPostoResponseDTO> mergeLines = postos.stream()
                .collect(Collectors.toMap(PostoCompleto::getId, ConsultaPostoResponseDTO::new,
                        ConsultaPostoResponseDTO::merge));

        return mergeLines.values();
    }
}
