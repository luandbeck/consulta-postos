package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.PostoCompleto;

import java.util.List;

public interface ConsultaPostoRepository {

    List<PostoCompleto> findAll();

    List<PostoCompleto> findByCidadeAndCombustivel(final String cidade, final Long combustivelId);

}
