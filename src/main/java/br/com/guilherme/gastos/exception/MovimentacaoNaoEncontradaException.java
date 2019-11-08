package br.com.guilherme.gastos.exception;

public class MovimentacaoNaoEncontradaException extends ServiceException {

    public MovimentacaoNaoEncontradaException() {

        super("Movimentacao não encontrada");
    }

}
