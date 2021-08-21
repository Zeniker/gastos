package br.com.guilherme.gastos.dto.origem.request;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestAlterarOrigemDTO {

    @NotNull
    @Mapping("nome")
    private String nome;

}
