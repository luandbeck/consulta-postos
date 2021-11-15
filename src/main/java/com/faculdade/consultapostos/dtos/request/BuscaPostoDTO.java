package com.faculdade.consultapostos.dtos.request;

import com.faculdade.consultapostos.enums.OrdemBusca;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuscaPostoDTO {

    @NotBlank
    private String endereco;

    @NotBlank
    private Long numeroEndereco;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotNull
    private Long combustivelId;

    private OrdemBusca ordemBusca;
}
