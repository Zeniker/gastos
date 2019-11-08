package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.service.GanhoService;
import br.com.guilherme.gastos.service.GastoService;
import br.com.guilherme.gastos.service.MovimentacaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SaldoService {

    private MovimentacaoService movimentacaoService;

    @Transactional(readOnly = true)
    public ResponseConsultarSaldoAnoMes consultarSaldoAnoMes(Integer ano, Integer mes){

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Movimentacao> movimentacoes = movimentacaoService.consultarMovimentacaoAnoMes(dataConsulta);

        BigDecimal totalGanhosMes = movimentacoes.stream()
                        .filter(m -> m.getTipoMovimentacao() == TipoMovimentacao.GANHO)
                        .map(Movimentacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastosMes = movimentacoes.stream()
                        .filter(m -> m.getTipoMovimentacao() == TipoMovimentacao.GASTO)
                        .map(Movimentacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoMes = totalGanhosMes.subtract(totalGastosMes);

        return new ResponseConsultarSaldoAnoMes(saldoMes, totalGanhosMes, totalGastosMes);
    }

}
