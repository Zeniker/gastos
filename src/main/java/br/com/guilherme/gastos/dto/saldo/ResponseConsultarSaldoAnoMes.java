package br.com.guilherme.gastos.dto.saldo;

import br.com.guilherme.gastos.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarSaldoAnoMes extends ResponseDTO {

    private BigDecimal saldo;
    private BigDecimal totalGanhos;
    private BigDecimal totalGastos;

    public ResponseConsultarSaldoAnoMes(String mensagemErro) {

        super(mensagemErro);
    }
}
