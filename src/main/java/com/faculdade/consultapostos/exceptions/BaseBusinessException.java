package com.faculdade.consultapostos.exceptions;

import com.faculdade.consultapostos.exceptions.domain.DefaultErrorData;
import com.faculdade.consultapostos.exceptions.domain.DefaultErrorMessage;
import com.faculdade.consultapostos.exceptions.domain.DefaultErrorResponse;
import com.faculdade.consultapostos.exceptions.domain.StandardError;
import com.faculdade.consultapostos.exceptions.enums.Errors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;


@Getter
@AllArgsConstructor
public abstract class BaseBusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;
    private final Errors errors;
    private final boolean printStack;
    private final List<DefaultErrorData> error;

    protected BaseBusinessException(final HttpStatus httpStatus, final Errors errors, final boolean printStack) {
        this.status = httpStatus;
        this.errors = errors;
        this.printStack = printStack;
        this.error = new ArrayList<>();
    }

    public final StandardError getStandardError(final String path, final Locale messageLocale) {
        return StandardError.builder()
                .path(path)
                .status(status.value())
                .printStack(printStack)
                .error(this.buildKnownErrorList(messageLocale))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    protected DefaultErrorResponse buildKnownErrorList(final Locale messageLocale) {
        return DefaultErrorResponse.builder()
                .code(errors.name())
                .message(errors.getMessage(messageLocale))
                .build();
    }

    public DefaultErrorResponse getErrors(final Locale messageLocale) {
        if (error != null && !error.isEmpty()) {
            return this.convert(error.stream().findFirst(), messageLocale);
        }
        return null;
    }

    private DefaultErrorResponse convert(final Optional<DefaultErrorData> defaultErrorData,
                                         final Locale messageLocale) {
        if (defaultErrorData.isPresent()) {
            final DefaultErrorData errorData = defaultErrorData.get();
            final Optional<DefaultErrorMessage> errorMessage = errorData.getMessage().stream()
                    .filter(Objects::nonNull)
                    .filter(lang -> lang.getLang().equals(messageLocale.getLanguage()))
                    .findFirst();

            if (errorMessage.isPresent()) {
                return DefaultErrorResponse.builder()
                        .code(errorData.getCode())
                        .message(errorMessage.get().getMessage())
                        .build();
            } else {
                final Optional<DefaultErrorMessage> msg = errorData.getMessage().stream()
                        .filter(Objects::nonNull)
                        .filter(lang -> lang.getLang().equals("en"))
                        .findFirst();

                if (msg.isPresent()) {
                    return DefaultErrorResponse.builder()
                            .code(errorData.getCode())
                            .message(msg.get().getMessage())
                            .build();
                }
            }
        }

        return null;
    }

    protected DefaultErrorResponse buildPrepaidErrorDataList(final Locale messageLocale) {
        return DefaultErrorResponse.builder()
                .code(errors.name())
                .message(errors.getMessage(messageLocale))
                .build();
    }
}
