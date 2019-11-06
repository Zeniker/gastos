package br.com.guilherme.gastos.dto.ganho;

import br.com.guilherme.gastos.domain.Ganho;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class GanhoDTO {

    private Integer id;
    private BigDecimal valor;
    private LocalDate dataEntrada;

    public GanhoDTO(Ganho ganho){
        this(ganho.getId(), ganho.getValor(), ganho.getDataEntrada());
    }

}
