package br.com.guilherme.gastos.exception;

public class GastoNaoEncontradoException extends ServiceException {

    public GastoNaoEncontradoException() {

        super("Gasto não encontrado");
    }
}
