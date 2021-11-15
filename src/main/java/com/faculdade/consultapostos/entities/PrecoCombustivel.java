package com.faculdade.consultapostos.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "preco_combustivel")
public class PrecoCombustivel {

    @Id
    @Column(name = "id_posto")
    private long postoId;

    @Id
    @Column(name = "id_combustivel")
    private long combustivelId;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "dh_inclusao")
    private LocalDateTime dataHoraInclusao;

}
