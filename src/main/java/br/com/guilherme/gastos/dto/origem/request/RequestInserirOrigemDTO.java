package br.com.guilherme.gastos.dto.origem.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInserirOrigemDTO {

    @NotNull
    private String nome;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

}
