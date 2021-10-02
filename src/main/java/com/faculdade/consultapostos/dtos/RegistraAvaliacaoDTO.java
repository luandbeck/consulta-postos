package com.faculdade.consultapostos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistraAvaliacaoDTO {

    @NotBlank
    private Long postoId;

    @NotBlank
    private Integer nota;
}
