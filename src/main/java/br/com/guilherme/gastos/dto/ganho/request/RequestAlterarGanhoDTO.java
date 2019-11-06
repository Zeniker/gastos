package br.com.guilherme.gastos.dto.ganho.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestAlterarGanhoDTO {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

    @NotNull
    private String descricao;

}
