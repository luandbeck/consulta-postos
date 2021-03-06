package com.faculdade.consultapostos.exceptions.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class StandardError {

    private String path;
    private Integer status;

    @JsonIgnore
    private Boolean printStack;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "pt-br", timezone = "Brazil/East")
    private Timestamp timestamp;

    private DefaultErrorResponse error;

}

