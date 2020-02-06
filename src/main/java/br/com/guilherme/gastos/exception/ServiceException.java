package br.com.guilherme.gastos.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 5110108278266107985L;

    ServiceException(String message) {

        super(message);
    }
}
