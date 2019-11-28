package br.com.guilherme.gastos.dto.origem.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestAlterarOrigemDTO {

    @NotNull
    private String nome;

}
