package br.com.guilherme.gastos.dto.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovimentacaoDTO {

    private Integer id;
    private BigDecimal valor;
    private LocalDate dataEntrada;
    private String descricao;
    private CategoriaDTO categoria;
    private OrigemDTO origem;
    private TipoMovimentacao tipoMovimentacao;

    public MovimentacaoDTO(Movimentacao movimentacao) {
        this(movimentacao.getId(),
                movimentacao.getValor(),
                movimentacao.getDataEntrada(),
                movimentacao.getDescricao(),
                movimentacao.getCategoria() != null ? new CategoriaDTO(movimentacao.getCategoria()) : null,
                movimentacao.getOrigem() != null ? new OrigemDTO(movimentacao.getOrigem()) : null,
                movimentacao.getTipoMovimentacao());
    }
}
