package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
    private GastoService gastoService;

    @Mock
    private GanhoService ganhoService;

    @InjectMocks
    private SaldoService saldoService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void consultarSaldoAnoMes() {
        //given
        Ganho ganho = new Ganho();
        ganho.setValor(new BigDecimal("400"));
        List<Ganho> ganhos = Arrays.asList(ganho, ganho);

        Gasto gasto = new Gasto();
        gasto.setValor(new BigDecimal("100"));
        List<Gasto> gastos = Arrays.asList(gasto, gasto, gasto);

        given(ganhoService.consultarGanhoAnoMes(any(LocalDate.class))).willReturn(ganhos);
        given(gastoService.consultarGastoAnoMes(any(LocalDate.class))).willReturn(gastos);

        //when
        ResponseConsultarSaldoAnoMes response = saldoService.consultarSaldoAnoMes(2019, 11);

        //then
        then(ganhoService).should().consultarGanhoAnoMes(any(LocalDate.class));
        then(gastoService).should().consultarGastoAnoMes(any(LocalDate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertEquals(new BigDecimal("800"), response.getTotalGanhos(), "Total de ganhos diferente do esperado");
        assertEquals(new BigDecimal("300"), response.getTotalGastos(), "Total de gastos diferente do esperado");
        assertEquals(new BigDecimal("500"), response.getSaldo(), "Saldo diferente do esperado");
    }
}