package br.com.guilherme.gastos.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {

        super(message);
    }
}
