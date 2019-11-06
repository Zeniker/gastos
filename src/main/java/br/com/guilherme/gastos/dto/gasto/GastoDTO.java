package br.com.guilherme.gastos.dto.gasto;

import br.com.guilherme.gastos.domain.Gasto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class GastoDTO {

    private Integer id;
    private BigDecimal valor;
    private LocalDate dataEntrada;
    private String descricao;

    public GastoDTO(Gasto gasto) {
        this(gasto.getId(), gasto.getValor(), gasto.getDataEntrada(), gasto.getDescricao());
    }
}
