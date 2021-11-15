package com.faculdade.consultapostos.controllers;

import com.faculdade.consultapostos.dtos.request.RegistraAvaliacaoDTO;
import com.faculdade.consultapostos.usecases.RegistraAvaliacaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoController {

    @Autowired
    private RegistraAvaliacaoUseCase registraAvaliacaoUseCase;

    @PostMapping
    public ResponseEntity<Void> inserirAvaliacao(@RequestBody final RegistraAvaliacaoDTO dto) {

        this.registraAvaliacaoUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}