package com.faculdade.consultapostos.controllers;

import com.faculdade.consultapostos.dtos.request.BuscaPostoDTO;
import com.faculdade.consultapostos.dtos.request.CriaPostoDTO;
import com.faculdade.consultapostos.dtos.response.ConsultaPostoResponseDTO;
import com.faculdade.consultapostos.usecases.BuscaPostoUseCase;
import com.faculdade.consultapostos.usecases.ConsultaPostoUseCase;
import com.faculdade.consultapostos.usecases.CriaPostoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/posto")
public class PostoController {


    @Autowired
    private CriaPostoUseCase criaPostoUseCase;

    @Autowired
    private ConsultaPostoUseCase consultaPostoUseCase;

    @Autowired
    private BuscaPostoUseCase buscaPostoUseCase;

    @PostMapping
    public ResponseEntity<Void> criarPosto(@RequestBody final CriaPostoDTO dto) {

        this.criaPostoUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/completo")
    public Collection<ConsultaPostoResponseDTO> getPostoCompleto() {
        return this.consultaPostoUseCase.execute();
    }

    @GetMapping("/buscar")
    public Collection<ConsultaPostoResponseDTO> getPosto(@RequestBody final BuscaPostoDTO dto) {
        return this.buscaPostoUseCase.execute(dto);
    }
}