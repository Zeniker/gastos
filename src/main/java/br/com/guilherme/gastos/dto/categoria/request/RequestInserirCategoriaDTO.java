package br.com.guilherme.gastos.dto.categoria.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInserirCategoriaDTO {

    @NotNull
    @Mapping("descricao")
    private String descricao;

    @NotNull
    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

}
