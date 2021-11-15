package com.faculdade.consultapostos.controllers;

import com.faculdade.consultapostos.dtos.request.RegistraCombustivelDTO;
import com.faculdade.consultapostos.dtos.request.RegistraPrecoDTO;
import com.faculdade.consultapostos.entities.Combustivel;
import com.faculdade.consultapostos.usecases.ConsultaCombustivelUseCase;
import com.faculdade.consultapostos.usecases.RegistraCombustivelUseCase;
import com.faculdade.consultapostos.usecases.RegistraPrecoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/combustivel")
public class CombustivelController {

    @Autowired
    private RegistraPrecoUseCase registraPrecoUseCase;

    @Autowired
    private ConsultaCombustivelUseCase consultaCombustivelUseCase;

    @Autowired
    private RegistraCombustivelUseCase registraCombustivelUseCase;

    @PostMapping("/preco")
    public ResponseEntity<Void> registrarPreco(@RequestBody final RegistraPrecoDTO dto) {

        this.registraPrecoUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tipo")
    public List<Combustivel> consultarCombustivel() {

        return this.consultaCombustivelUseCase.execute();
    }

    @PostMapping("/tipo")
    public ResponseEntity<Void> inserirCombustivel(@RequestBody final RegistraCombustivelDTO dto) {

        this.registraCombustivelUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}