package br.com.guilherme.gastos.dto.sessao.request;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestRegistrarDTO {

    @NotNull
    @Mapping("nome")
    private String nome;

    @NotNull
    @Mapping("email")
    private String email;

    @NotNull
    @Mapping("senha")
    private String senha;
}
