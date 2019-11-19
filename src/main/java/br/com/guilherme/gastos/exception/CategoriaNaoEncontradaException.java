package br.com.guilherme.gastos.exception;

public class CategoriaNaoEncontradaException extends ServiceException {

    public CategoriaNaoEncontradaException() {

        super("Categoria não encontrada");
    }
}
