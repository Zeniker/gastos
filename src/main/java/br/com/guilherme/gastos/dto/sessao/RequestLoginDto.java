package br.com.guilherme.gastos.dto.sessao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestLoginDto {

    @NotNull
    private String usuario;

    @NotNull
    private String senha;
}
