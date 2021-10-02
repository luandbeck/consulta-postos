package com.faculdade.consultapostos.configurations;

import com.faculdade.consultapostos.exceptions.domain.DefaultErrorData;
import com.faculdade.consultapostos.exceptions.domain.DefaultErrorMessage;
import com.faculdade.consultapostos.exceptions.domain.ErrorMap;
import com.faculdade.consultapostos.exceptions.domain.ExternalErrorData;
import com.faculdade.consultapostos.exceptions.domain.InternalErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ErrorMapper {

    private static final String GENERIC_ERROR_CODE = "ATM001";

    private ErrorConfig errorConfig;

    public List<DefaultErrorData> parse(final ExternalErrorData externalError) {
        if (externalError != null) {
            return parseErrorData(Collections.singletonList(externalError));
        }
        return Collections.emptyList();
    }

    private List<DefaultErrorData> parseErrorData(final List<ExternalErrorData> externalErrorsList) {
        if (errorConfig != null) {
            final List<ErrorMap> configErrorMapping = errorConfig.getErrorMapping();

            if (externalErrorsList != null && configErrorMapping != null && !externalErrorsList.isEmpty() && !configErrorMapping.isEmpty()) {
                final Set<DefaultErrorData> internalErrorDataSet = new HashSet<>();
                final DefaultErrorData genericError = findGenericError();
                findKnownErrors(externalErrorsList, configErrorMapping, internalErrorDataSet, genericError);
                return new ArrayList<>(internalErrorDataSet);
            }

        }

        log.error("Error Config is not defined");

        return Collections.emptyList();
    }

    private void findKnownErrors(final List<ExternalErrorData> errorDataList, final List<ErrorMap> configErrorMapping,
                                 final Set<DefaultErrorData> internalErrorDataSet, final DefaultErrorData genericError) {
        errorDataList.forEach(errorData -> {
            final ExternalErrorData parsedErrorData = ExternalErrorData.builder()
                    .code(errorData.getCode() != null ? errorData.getCode() : "")
                    .message(errorData.getMessage() != null ? errorData.getMessage() : "")
                    .build();

            log.info("Starting Error Mapper parsing process");

            final List<ErrorMap> errorMapping = configErrorMapping.stream()
                    .filter(errorMap -> parsedErrorData.getCode().equals(errorMap.getExternalCode()))
                    .collect(Collectors.toList());

            final boolean matchFound = extractPrePaidErrors(internalErrorDataSet, parsedErrorData, errorMapping);

            if (!matchFound && !StringUtils.isEmpty(genericError)) {
                internalErrorDataSet.add(genericError);

                log.info("Error Mapper parsing process finished");
            }
        });
    }

    private boolean extractPrePaidErrors(final Set<DefaultErrorData> internalErrorDataSet, final ExternalErrorData errorData,
                                         final List<ErrorMap> errorMapping) {
        boolean matchFound = false;
        for (final ErrorMap errorMap : errorMapping) {
            final Optional<DefaultErrorData> errorMatch = matchErrorMessage(errorMap, errorData.getMessage());
            if (errorMatch.isPresent()) {
                final DefaultErrorData errorMatchData = errorMatch.get();
                internalErrorDataSet.add(errorMatchData);
                matchFound = true;

                log.info("Error Mapper parsing process finished");

                break;
            }
        }
        return matchFound;
    }

    /**
     * Compares the Error Map message pattern with the given Error Message.
     *
     * @param errorMap             Error Map with the pattern, message and code to be used
     * @param externalErrorMessage Message to check for pattern
     * @return Optional of the code and message that will be presented for the given pattern
     */
    private Optional<DefaultErrorData> matchErrorMessage(final ErrorMap errorMap, final String externalErrorMessage) {
        final Pattern errorMessagePattern = Pattern.compile(errorMap.getExternalMessage());
        final Matcher errorMessageMatcher = errorMessagePattern.matcher(externalErrorMessage);
        if (errorMessageMatcher.matches()) {
            int groupCount = errorMessageMatcher.groupCount();
            if (groupCount > 0) {
                final List<String> messageArguments = new ArrayList<>();
                for (int i = 1; i <= groupCount; ++i) {
                    messageArguments.add(errorMessageMatcher.group(i));
                }
                return Optional.of(DefaultErrorData.builder().code(errorMap.getInternalCode())
                        .message(convertMessageList(errorMap.getInternalMessage(), messageArguments.toArray())).build());
            }
            return Optional.of(DefaultErrorData.builder().code(errorMap.getInternalCode()).message(convertMessageList(errorMap.getInternalMessage())).build());
        }
        return Optional.empty();
    }


    /**
     * Convert a list of internal message to translated form with regex
     *
     * @param messages
     * @param args
     * @return
     */
    private List<DefaultErrorMessage> convertMessageList(final List<InternalErrorMessage> messages, final Object[] args) {
        return messages.stream().map(message -> convertMessage(message, args)).collect(Collectors.toList());
    }

    private DefaultErrorMessage convertMessage(final InternalErrorMessage message, final Object[] args) {
        return DefaultErrorMessage.builder()
                .lang(message.getLang())
                .message(MessageFormat.format(message.getMessage(), args))
                .build();
    }

    /**
     * Convert a list of internal message to translated form without regex
     *
     * @param messages
     * @return
     */
    private List<DefaultErrorMessage> convertMessageList(final List<InternalErrorMessage> messages) {
        return messages.stream().map(this::convertMessage).collect(Collectors.toList());
    }

    private DefaultErrorMessage convertMessage(final InternalErrorMessage message) {
        return DefaultErrorMessage.builder()
                .lang(message.getLang())
                .message(message.getMessage())
                .build();
    }

    /**
     * Find the generic error configured in the Error Config.
     *
     * @return Optional of ErrorData with the code/message of the Generic Error
     */
    private DefaultErrorData findGenericError() {
        final Optional<DefaultErrorData> genericError = errorConfig.getErrorMapping().stream()
                .filter(errorMap -> GENERIC_ERROR_CODE.equals(errorMap.getInternalCode()))
                .map(errorMap -> DefaultErrorData.builder().code(errorMap.getInternalCode()).message(convertMessageList(errorMap.getInternalMessage())).build()).findAny();

        if (!genericError.isPresent()) {
            log.error("Unable to find the Generic Error in Error Configuration");
        }

        return genericError.orElse(null);
    }
}
