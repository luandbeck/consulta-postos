package com.faculdade.consultapostos.exceptions.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMap {

    private String internalCode;
    private List<InternalErrorMessage> internalMessage;
    private String externalCode;
    private String externalMessage;
}
