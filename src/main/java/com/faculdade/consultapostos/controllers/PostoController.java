package com.faculdade.consultapostos.controllers;

import com.faculdade.consultapostos.dtos.request.BuscaPostoDTO;
import com.faculdade.consultapostos.dtos.request.CriaPostoDTO;
import com.faculdade.consultapostos.dtos.response.ConsultaPostoResponseDTO;
import com.faculdade.consultapostos.enums.OrdemBusca;
import com.faculdade.consultapostos.usecases.BuscaPostoUseCase;
import com.faculdade.consultapostos.usecases.ConsultaPostoUseCase;
import com.faculdade.consultapostos.usecases.CriaPostoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    public Collection<ConsultaPostoResponseDTO> getPosto(@NotBlank @RequestParam final String endereco,
                                                         @NotNull @RequestParam final Long numeroEndereco,
                                                         @NotBlank @RequestParam final String cidade,
                                                         @NotBlank @RequestParam final String estado,
                                                         @NotBlank @RequestParam final Long combustivelId,
                                                         @NotNull @RequestParam final OrdemBusca ordemBusca) {

        final BuscaPostoDTO dto = BuscaPostoDTO.builder()
                .endereco(endereco)
                .numeroEndereco(numeroEndereco)
                .cidade(cidade)
                .estado(estado)
                .combustivelId(combustivelId)
                .ordemBusca(ordemBusca)
                .build();

        return this.buscaPostoUseCase.execute(dto);
    }
}