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
@Table(name = "combustivel")
public class Combustivel {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "descricao")
    private String descricao;

}
