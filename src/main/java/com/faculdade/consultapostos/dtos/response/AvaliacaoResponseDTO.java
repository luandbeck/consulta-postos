package com.faculdade.consultapostos.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AvaliacaoResponseDTO {

    private Double media;
    private Integer quantidadeAvaliacao;
}
