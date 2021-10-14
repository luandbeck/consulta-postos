package com.faculdade.consultapostos.exceptions.handler;

import com.faculdade.consultapostos.exceptions.domain.DefaultErrorResponse;
import com.faculdade.consultapostos.exceptions.domain.StandardError;
import com.faculdade.consultapostos.exceptions.enums.Errors;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends BaseExceptionHandler {

    public ControllerExceptionHandler() {
        super(log);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(final MethodArgumentNotValidException ex,
                                                                         final HttpServletRequest request) {
        final DefaultErrorResponse defaultErrorResponse =
                this.convertToDefaultErrorData(ex.getBindingResult().getFieldErrors(), request.getLocale());

        final StandardError error = StandardError.builder()
                .error(defaultErrorResponse)
                .printStack(Boolean.FALSE)
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<StandardError> bindException(final BindException ex, final HttpServletRequest request) {
        final DefaultErrorResponse defaultErrorResponse =
                this.convertToDefaultErrorData(ex.getBindingResult().getFieldErrors(), request.getLocale());

        final StandardError error = StandardError.builder()
                .error(defaultErrorResponse)
                .printStack(Boolean.FALSE)
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(final HttpMessageNotReadableException ex,
                                                                         final HttpServletRequest request) {
        final JsonMappingException jsonError = (JsonMappingException) ex.getCause();
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages/messages", request.getLocale());
        final String value = ((InvalidFormatException) ex.getCause()).getValue().toString();
        final List<FieldError> fieldList = new ArrayList<>();
        fieldList.add(new FieldError(
                jsonError.getPath().get(0).getFieldName(),
                jsonError.getPath().get(0).getFieldName(),
                new String(resourceBundle.getString("generic.parseError").getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8) + value));
        final DefaultErrorResponse defaultErrorResponse = this.convertToDefaultErrorData(fieldList, request.getLocale());

        final StandardError error = StandardError.builder()
                .error(defaultErrorResponse)
                .printStack(Boolean.FALSE)
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<StandardError> missingRequestHeaderException(final MissingRequestHeaderException ex,
                                                                       final HttpServletRequest request) {
        final StandardError error = StandardError.builder()
                .error(this.convertToDefaultErrorData(
                        Collections.singletonMap(ex.getHeaderName(), Arrays.asList(ex.getMessage())),
                        request.getLocale()))
                .printStack(Boolean.FALSE)
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<StandardError> constraintViolationException(final ConstraintViolationException ex,
//                                                                      final HttpServletRequest request) {
//        final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages/messages", request.getLocale());
//        final Map<String, List<String>> fields = new HashMap<>();
//        ex.getConstraintViolations().forEach(constraint -> {
//            final var constraintImpl = (ConstraintViolationImpl) constraint;
//            final String messageValidCharacters = constraintImpl.getMessage().replace("{", "").replace("}", "");
//            final List<String> list =
//                    Arrays.asList(new String(resourceBundle.getString(messageValidCharacters).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            fields.put("header", list);
//        });
//
//        final StandardError error = StandardError.builder()
//                .error(this.convertToDefaultErrorData(fields, request.getLocale()))
//                .printStack(Boolean.FALSE)
//                .path(request.getRequestURI())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .timestamp(new Timestamp(System.currentTimeMillis()))
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }

    private DefaultErrorResponse convertToDefaultErrorData(final List<FieldError> errorValidationList,
                                                           final Locale locale) {
        final Map<String, List<String>> fields = errorValidationList.stream()
                .collect(groupingBy(FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList())));

        return this.convertToDefaultErrorData(fields, locale);
    }

    private DefaultErrorResponse convertToDefaultErrorData(final Map<String, List<String>> fields,
                                                           final Locale locale) {
        return DefaultErrorResponse.builder()
                .fields(fields)
                .code(Errors.ATM002.name())
                .message(Errors.ATM002.getMessage(locale))
                .build();
    }

}

