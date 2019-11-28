package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SaldoServiceTest {

    @Mock
    private MovimentacaoService movimentacaoService;

    @InjectMocks
    private SaldoService saldoService;

    @DisplayName("Consultar Saldo no ano/mes - Saldo positivo")
    @Test
    void consultarSaldoAnoMesSaldoPositivo() {
        //given
        Movimentacao ganho = new Movimentacao();
        ganho.setValor(new BigDecimal("400"));
        ganho.setTipoMovimentacao(TipoMovimentacao.GANHO);

        Movimentacao gasto = new Movimentacao();
        gasto.setValor(new BigDecimal("100"));
        gasto.setTipoMovimentacao(TipoMovimentacao.GASTO);

        List<Movimentacao> movimentacoes = Arrays.asList(gasto, gasto, gasto, ganho, ganho);

        given(movimentacaoService.consultarMovimentacaoAnoMes(any(LocalDate.class))).willReturn(movimentacoes);

        //when
        ResponseConsultarSaldoAnoMes response = saldoService.consultarSaldoAnoMes(2019, 11);

        //then
        then(movimentacaoService).should().consultarMovimentacaoAnoMes(any(LocalDate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertEquals(new BigDecimal("800"), response.getTotalGanhos(), "Total de ganhos diferente do esperado");
        assertEquals(new BigDecimal("300"), response.getTotalGastos(), "Total de gastos diferente do esperado");
        assertEquals(new BigDecimal("500"), response.getSaldo(), "Saldo diferente do esperado");
    }

    @DisplayName("Consultar Saldo no ano/mes - Saldo negativo")
    @Test
    void consultarSaldoAnoMesSaldoNegativo() {
        //given
        Movimentacao gasto = new Movimentacao();
        gasto.setValor(new BigDecimal("400"));
        gasto.setTipoMovimentacao(TipoMovimentacao.GASTO);

        Movimentacao ganho = new Movimentacao();
        ganho.setValor(new BigDecimal("100"));
        ganho.setTipoMovimentacao(TipoMovimentacao.GANHO);

        List<Movimentacao> movimentacoes = Arrays.asList(ganho, ganho, gasto, gasto, gasto);

        given(movimentacaoService.consultarMovimentacaoAnoMes(any(LocalDate.class))).willReturn(movimentacoes);

        //when
        ResponseConsultarSaldoAnoMes response = saldoService.consultarSaldoAnoMes(2019, 11);

        //then
        then(movimentacaoService).should().consultarMovimentacaoAnoMes(any(LocalDate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertEquals(new BigDecimal("200"), response.getTotalGanhos(), "Total de ganhos diferente do esperado");
        assertEquals(new BigDecimal("1200"), response.getTotalGastos(), "Total de gastos diferente do esperado");
        assertEquals(new BigDecimal("-1000"), response.getSaldo(), "Saldo diferente do esperado");
    }

    @DisplayName("Consultar Saldo no ano/mes - Excecao Conversao Ano")
    @Test
    void consultarSaldoAnoMesExcecaoConversaoAno() {

        assertThrows(DateTimeException.class, () -> saldoService.consultarSaldoAnoMes(1203812931, 11));
    }

    @DisplayName("Consultar Saldo no ano/mes - Excecao Conversao Mes")
    @Test
    void consultarSaldoAnoMesExcecaoConversaoMes() {

        assertThrows(DateTimeException.class, () -> saldoService.consultarSaldoAnoMes(2019, 13));
    }
}