package br.com.guilherme.gastos.exception;

public class UsuarioNaoEncontradoException extends ServiceRuntimeException {

    private static final long serialVersionUID = -2324468842619898746L;

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado");
    }
}
