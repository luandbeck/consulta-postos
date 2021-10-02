package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.AvaliacaoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoHistoricoRepository extends JpaRepository<AvaliacaoHistorico, Long> {
}
