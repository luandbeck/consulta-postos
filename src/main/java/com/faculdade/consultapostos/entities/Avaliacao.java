package com.faculdade.consultapostos.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao")
public class Avaliacao {

    @OneToOne
    @JoinColumn(name = "id_posto")
    private Posto posto;

    @Column(name = "media")
    private Double media;
}