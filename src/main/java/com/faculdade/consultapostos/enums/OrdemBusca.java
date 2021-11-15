package com.faculdade.consultapostos.enums;

import lombok.Getter;

@Getter
public enum OrdemBusca {
    COMBUSTIVEL("COMBUSTIVEL"),
    DISTANCIA("DISTANCIA");

    private final String tipo;

    OrdemBusca(final String tipo) {
        this.tipo = tipo;
    }
}
