package com.faculdade.consultapostos.controllers;

import com.faculdade.consultapostos.dtos.CriaPostoDTO;
import com.faculdade.consultapostos.entities.Posto;
import com.faculdade.consultapostos.exceptions.UserNotFoundException;
import com.faculdade.consultapostos.repositories.PostoRepository;
import com.faculdade.consultapostos.usecases.CriaPostoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postos")
public class PostoController {

    @Autowired
    private PostoRepository repository;

    @Autowired
    private CriaPostoUseCase criaPostoUseCase;

    @GetMapping
    public List<Posto> getPosto() {
        if (true) {
            throw new UserNotFoundException();
        }

        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> criarPosto(@RequestBody final CriaPostoDTO dto) {

        this.criaPostoUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
