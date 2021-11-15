package com.faculdade.consultapostos.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao_historico")
public class AvaliacaoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "id_posto")
    private long postoId;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "dh_inclusao")
    private LocalDateTime dataHoraInclusao;

}
