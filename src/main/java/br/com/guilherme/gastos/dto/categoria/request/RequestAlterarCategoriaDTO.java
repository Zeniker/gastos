package br.com.guilherme.gastos.dto.categoria.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestAlterarCategoriaDTO {

    @NotNull
    private String descricao;

}
