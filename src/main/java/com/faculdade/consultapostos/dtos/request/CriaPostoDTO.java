package com.faculdade.consultapostos.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CriaPostoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private Long numeroEndereco;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;
}
