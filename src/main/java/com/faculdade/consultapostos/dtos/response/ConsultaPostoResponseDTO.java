package com.faculdade.consultapostos.dtos.response;


import com.faculdade.consultapostos.entities.PostoCompleto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ConsultaPostoResponseDTO {

    private long id;
    private String nome;
    private String endereco;
    private Long numeroEndereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String latitude;
    private String longitude;
    private Double distancia;

    private AvaliacaoResponseDTO avaliacao;
    private List<CombustivelResponseDTO> combustiveis;

    public ConsultaPostoResponseDTO(final PostoCompleto posto) {
        final AvaliacaoResponseDTO avaliacao = AvaliacaoResponseDTO.builder()
                .media(posto.getMediaAvaliacao())
                .quantidadeAvaliacao(posto.getQuantidadeAvaliacao())
                .build();

        final CombustivelResponseDTO combustivel = CombustivelResponseDTO.builder()
                .combustivelId(posto.getCombustivelId())
                .descricao(posto.getCombustivelDescricao())
                .valor(posto.getValorCombustivel())
                .build();

        this.id = posto.getId();
        this.nome = posto.getNome();
        this.endereco = posto.getEndereco();
        this.numeroEndereco = posto.getNumeroEndereco();
        this.bairro = posto.getBairro();
        this.cidade = posto.getCidade();
        this.estado = posto.getEstado();
        this.cep = posto.getCep();
        this.latitude = posto.getLatitude();
        this.longitude = posto.getLongitude();
        this.avaliacao = avaliacao;

        if (combustivel.getCombustivelId() != 0) {
            this.combustiveis = new ArrayList<>(Collections.singletonList(combustivel));
        }
    }

    public ConsultaPostoResponseDTO merge(final ConsultaPostoResponseDTO another) {
        this.combustiveis.addAll(another.combustiveis);
        return this;
    }

    public void calcularDistancia(final String latitudeLocal, final String longitudeLocal) {
        final double latLocal = Double.parseDouble(latitudeLocal);
        final double lonLocal = Double.parseDouble(longitudeLocal);
        final double latPosto = Double.parseDouble(this.getLatitude());
        final double lonPosto = Double.parseDouble(this.getLongitude());

        if ((latLocal == latPosto) && (lonLocal == lonPosto)) {
            this.distancia = (double) 0;
        } else {
            final double theta = lonLocal - lonPosto;
            double dist =
                    Math.sin(Math.toRadians(latLocal)) * Math.sin(Math.toRadians(latPosto)) + Math.cos(Math.toRadians(latLocal)) * Math.cos(Math.toRadians(latPosto)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            //Convert KM
            dist = dist * 1.609344;

            this.distancia = dist;
        }
    }


}
