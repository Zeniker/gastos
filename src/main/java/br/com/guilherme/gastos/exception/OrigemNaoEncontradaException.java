package br.com.guilherme.gastos.exception;

public class OrigemNaoEncontradaException extends ServiceException {

    public OrigemNaoEncontradaException() {

        super("Origem não encontrada");
    }
}
