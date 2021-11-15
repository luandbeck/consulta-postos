package com.faculdade.consultapostos.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @Column(name = "id_posto")
    private long postoId;

    @Column(name = "media")
    private Double media;

    @Column(name = "quantidade_avaliacao")
    private Integer quantidadeAvaliacao;

}
