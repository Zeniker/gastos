package br.com.guilherme.gastos.dto.ganho.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestInserirGanhoDTO {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

}
