package br.com.guilherme.gastos.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

@Data
class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    private LinkedList<FieldError> fieldErrors = new LinkedList<>();

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    private void setStatus(HttpStatus status) {

        if (status != null) {
            this.status = status.value();
            this.error = status.name();
        }
    }

    ApiError(HttpStatus status, String message) {
        this();

        setStatus(status);

        this.message = message;

    }

    ApiError(HttpStatus status, String message, String path) {
        this();

        setStatus(status);

        this.message = message;
        this.path = path;

    }

    public void addFieldError(String object, String field, String message){
        fieldErrors.add(FieldError.builder()
                .object(object)
                .field(field)
                .message(message).build()
        );
    }
}
