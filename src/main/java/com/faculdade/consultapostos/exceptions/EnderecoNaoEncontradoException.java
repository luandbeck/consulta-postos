package com.faculdade.consultapostos.exceptions;

import com.faculdade.consultapostos.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class EnderecoNaoEncontradoException extends BaseBusinessException {

    public EnderecoNaoEncontradoException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Errors.ATM003, false);
    }

}
