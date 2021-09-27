package com.faculdade.consultapostos.exceptions;

import com.faculdade.consultapostos.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseBusinessException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, Errors.ATM005, false);
    }

}
