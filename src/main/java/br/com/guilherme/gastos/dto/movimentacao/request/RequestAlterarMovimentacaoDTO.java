package br.com.guilherme.gastos.dto.movimentacao.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestAlterarMovimentacaoDTO {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

    @NotNull
    private String descricao;

    @NotNull
    private Integer categoria;

}
