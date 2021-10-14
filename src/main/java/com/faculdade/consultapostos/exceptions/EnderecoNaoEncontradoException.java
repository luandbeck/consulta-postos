package com.faculdade.consultapostos.exceptions;

import com.faculdade.consultapostos.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class EnderecoNaoEncontradoException extends BaseBusinessException {

    public EnderecoNaoEncontradoException() {
        super(HttpStatus.PRECONDITION_FAILED, Errors.ATM004, false);
    }

}
