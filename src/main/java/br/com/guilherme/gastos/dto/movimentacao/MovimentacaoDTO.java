package br.com.guilherme.gastos.dto.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MovimentacaoDTO {

    private Integer id;
    private BigDecimal valor;
    private LocalDate dataEntrada;
    private String descricao;

    public MovimentacaoDTO(Movimentacao movimentacao) {
        this(movimentacao.getId(), movimentacao.getValor(), movimentacao.getDataEntrada(), movimentacao.getDescricao());
    }
}
