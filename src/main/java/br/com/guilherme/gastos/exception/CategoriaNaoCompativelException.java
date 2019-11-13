package br.com.guilherme.gastos.exception;

public class CategoriaNaoCompativelException extends ServiceException {

    public CategoriaNaoCompativelException() {

        super("Categoria não é compatível com o tipo de movimentação");
    }
}
