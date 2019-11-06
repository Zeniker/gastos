package br.com.guilherme.gastos.exception;

public class GanhoNaoEncontradoException extends ServiceException {

    public GanhoNaoEncontradoException() {
        super("Ganho não encontrado");
    }
}
