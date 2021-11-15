package com.faculdade.consultapostos.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistraPrecoDTO {

    @NotBlank
    private Long postoId;

    @NotBlank
    private Long combustivelId;

    @NotBlank
    private Double preco;
}