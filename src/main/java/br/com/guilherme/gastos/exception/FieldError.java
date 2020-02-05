package br.com.guilherme.gastos.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {

    private String object;

    private String field;

    private String message;
}
