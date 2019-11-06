package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SaldoService {

    private GastoService gastoService;
    private GanhoService ganhoService;

    @Transactional(readOnly = true)
    public ResponseConsultarSaldoAnoMes consultarSaldoAnoMes(Integer ano, Integer mes){

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Ganho> ganhos = ganhoService.consultarGanhoAnoMes(dataConsulta);
        List<Gasto> gastos = gastoService.consultarGastoAnoMes(dataConsulta);

        BigDecimal totalGanhosMes = ganhos.stream().map(Ganho::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGastosMes = gastos.stream().map(Gasto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoMes = totalGanhosMes.subtract(totalGastosMes);

        return new ResponseConsultarSaldoAnoMes(saldoMes, totalGanhosMes, totalGastosMes);
    }

}
