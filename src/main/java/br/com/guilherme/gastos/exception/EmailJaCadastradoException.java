package br.com.guilherme.gastos.exception;

public class EmailJaCadastradoException extends ServiceException {

    public EmailJaCadastradoException() {
        super("O e-mail informado já está cadastrado no sistema");
    }
}
