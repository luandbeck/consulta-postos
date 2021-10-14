package com.faculdade.consultapostos.exceptions.handler;

import com.faculdade.consultapostos.exceptions.BaseBusinessException;
import com.faculdade.consultapostos.exceptions.domain.DefaultErrorResponse;
import com.faculdade.consultapostos.exceptions.domain.StandardError;
import com.faculdade.consultapostos.exceptions.enums.Errors;
import feign.RetryableException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;


public abstract class BaseExceptionHandler {

    private static final StandardError DEFAULT_ERROR = StandardError.builder()
            .status(INTERNAL_SERVER_ERROR.value())
            .printStack(Boolean.TRUE)
            .timestamp(new Timestamp(System.currentTimeMillis()))
            .build();

    private final Logger log;
    private final Map<Class<?>, StandardError> exceptionMappings = new HashMap<>();

    BaseExceptionHandler(final Logger log) {
        this.log = log;
        this.registerMapping(ServletRequestBindingException.class, BAD_REQUEST, Boolean.TRUE);
        this.registerMapping(HttpMessageNotReadableException.class, BAD_REQUEST, Boolean.TRUE);
        this.registerMapping(MethodArgumentTypeMismatchException.class, BAD_REQUEST, Boolean.TRUE);
        this.registerMapping(MissingServletRequestParameterException.class, BAD_REQUEST, Boolean.TRUE);
        this.registerMapping(HttpRequestMethodNotSupportedException.class, METHOD_NOT_ALLOWED, Boolean.TRUE);
        this.registerMapping(HttpMediaTypeException.class, UNSUPPORTED_MEDIA_TYPE, Boolean.TRUE);
        this.registerMapping(RetryableException.class, INTERNAL_SERVER_ERROR, Boolean.TRUE);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<StandardError> handlerException(final Throwable ex, final HttpServletRequest request) {
        final StandardError mapping = exceptionMappings.getOrDefault(ex.getClass(), DEFAULT_ERROR);

        final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.builder()
                .code(Errors.ATM001.name())
                .message(Errors.ATM001.getMessage(request.getLocale()))
                .build();

        final StandardError error = StandardError.builder()
                .error(defaultErrorResponse)
                .status(mapping.getStatus())
                .path(request.getRequestURI())
                .printStack(mapping.getPrintStack())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        if (Boolean.TRUE.equals(mapping.getPrintStack())) {
            log.error("Exception caught", ex);
        }

        return ResponseEntity
                .status(mapping.getStatus())
                .body(error);
    }

    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<StandardError> handlerBusinessException(final BaseBusinessException bbe,
                                                                  final HttpServletRequest request) {
        if (bbe.isPrintStack()) {
            log.error("Exception caught", bbe);
        }

        return ResponseEntity
                .status(bbe.getStatus())
                .body(bbe.getStandardError(request.getRequestURI(), request.getLocale()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handlerConstraintViolationException(final ConstraintViolationException bbe,
                                                                             final HttpServletRequest request) {
        final StandardError error = StandardError.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .timestamp(Timestamp.from(Instant.now()))
                .error(DefaultErrorResponse.builder()
                        .code(Errors.ATM003.name())
                        .message(Errors.ATM003.getMessage(request.getLocale()))
                        .build())
                .build();

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(error);
    }

    private void registerMapping(
            final Class<?> clazz,
            final HttpStatus status,
            final Boolean printStack) {

        exceptionMappings.put(clazz, StandardError.builder()
                .status(status.value())
                .printStack(printStack)
                .build());
    }
}
