package com.faculdade.consultapostos.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao_historico")
public class AvaliacaoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_posto")
    private Posto posto;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "dh_inclusao")
    private LocalDateTime dataHoraInclusao;
}
