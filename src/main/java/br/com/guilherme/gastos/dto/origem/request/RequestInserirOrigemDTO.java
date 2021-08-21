package br.com.guilherme.gastos.dto.origem.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInserirOrigemDTO {

    @NotNull
    @Mapping("nome")
    private String nome;

    @NotNull
    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

}
