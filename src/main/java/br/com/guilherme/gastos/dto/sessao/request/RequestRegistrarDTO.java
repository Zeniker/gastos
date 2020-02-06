package br.com.guilherme.gastos.dto.sessao.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestRegistrarDTO {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String senha;
}
