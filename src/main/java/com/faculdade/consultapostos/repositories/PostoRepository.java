package com.faculdade.consultapostos.repositories;

import com.faculdade.consultapostos.entities.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoRepository extends JpaRepository<Posto, Long> {
}
