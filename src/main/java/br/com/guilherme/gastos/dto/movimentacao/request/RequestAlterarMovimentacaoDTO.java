package br.com.guilherme.gastos.dto.movimentacao.request;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestAlterarMovimentacaoDTO {

    @NotNull
    @Mapping("valor")
    private BigDecimal valor;

    @NotNull
    @Mapping("dataEntrada")
    private LocalDate dataEntrada;

    @NotNull
    @Mapping("descricao")
    private String descricao;

    @NotNull
    @Mapping("this")
    private Integer categoria;

    @NotNull
    @Mapping("this")
    private Integer origem;

}
