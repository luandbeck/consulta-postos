package com.faculdade.consultapostos.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostoCompleto {

    private long id;
    private String nome;
    private String endereco;
    private Long numeroEndereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String latitude;
    private String longitude;
    private String cep;

    private Double mediaAvaliacao;
    private Integer quantidadeAvaliacao;

    private long combustivelId;
    private String combustivelDescricao;
    private Double valorCombustivel;
}
