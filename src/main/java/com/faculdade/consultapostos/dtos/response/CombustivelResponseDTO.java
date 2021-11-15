package com.faculdade.consultapostos.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@NonNull
public class CombustivelResponseDTO {

    private long combustivelId;
    private String descricao;
    private Double valor;
}
