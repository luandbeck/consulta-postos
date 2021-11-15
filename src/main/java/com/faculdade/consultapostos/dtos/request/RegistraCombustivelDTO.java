package com.faculdade.consultapostos.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistraCombustivelDTO {

    @NotBlank
    private String descricao;
}