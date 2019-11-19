package br.com.guilherme.gastos.dto.categoria.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInserirCategoriaDTO {

    @NotNull
    private String descricao;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

}
