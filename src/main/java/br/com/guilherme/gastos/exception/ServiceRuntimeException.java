package br.com.guilherme.gastos.exception;

public class ServiceRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8877453441956978200L;

    ServiceRuntimeException(String message) {

        super(message);
    }
}
