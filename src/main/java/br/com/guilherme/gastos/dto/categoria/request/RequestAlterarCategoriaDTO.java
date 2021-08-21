package br.com.guilherme.gastos.dto.categoria.request;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestAlterarCategoriaDTO {

    @NotNull
    @Mapping("descricao")
    private String descricao;

}
