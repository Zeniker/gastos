package br.com.guilherme.gastos.dto.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {

    @Mapping("id")
    private Integer id;

    @Mapping("valor")
    private BigDecimal valor;

    @Mapping("dataEntrada")
    private LocalDate dataEntrada;

    @Mapping("descricao")
    private String descricao;

    @Mapping("categoria")
    private CategoriaDTO categoria;

    @Mapping("origem")
    private OrigemDTO origem;

    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;
}
