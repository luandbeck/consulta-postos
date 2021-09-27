package com.faculdade.consultapostos.exceptions.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorMessage implements Serializable {

    private static final long serialVersionUID = 2148477498188170080L;

    private String lang;
    private String message;
}
