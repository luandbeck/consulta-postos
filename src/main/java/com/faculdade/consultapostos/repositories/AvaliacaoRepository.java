package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Avaliacao;

public interface AvaliacaoRepository {

    void save(Avaliacao avaliacao);
    
    Avaliacao findById(final Long postoId);


}
