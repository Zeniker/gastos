package br.com.guilherme.gastos.exception;

public class OrigemNaoCompativelException extends ServiceException {

    public OrigemNaoCompativelException() {

        super("Origem não é compatível com o tipo de movimentação");
    }
}
