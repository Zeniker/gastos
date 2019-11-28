package br.com.guilherme.gastos.dto.movimentacao.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestInserirMovimentacaoDTO {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

    @NotNull
    private String descricao;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    private Integer categoria;

    @NotNull
    private Integer origem;

}
