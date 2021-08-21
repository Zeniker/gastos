package br.com.guilherme.gastos.dto.movimentacao.request;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestInserirMovimentacaoDTO {

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
    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    @Mapping("this")
    private Integer categoria;

    @NotNull
    @Mapping("this")
    private Integer origem;

}
