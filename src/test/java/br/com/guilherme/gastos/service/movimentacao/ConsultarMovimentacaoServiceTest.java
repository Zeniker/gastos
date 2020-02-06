package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ConsultarMovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @InjectMocks
    private ConsultarMovimentacaoService service;

    private Iterable movimentacoes;

    @BeforeEach
    void setUp() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setCategoria(new Categoria());
        movimentacao.setValor(new BigDecimal("200"));

        movimentacoes = Arrays.asList(movimentacao, movimentacao);
    }

    @DisplayName("Consultar Movimentacao por ano/mes por LocalDate")
    @Test
    void consultarMovimentacaoAnoMesPorLocalDate() {
        //given
        given(repository.findAll(any(Predicate.class))).willReturn(movimentacoes);

        //when
        List<Movimentacao> movimentacoesEncontradas = service.consultarMovimentacaoAnoMes(LocalDate.now(),
                TipoMovimentacao.GASTO);

        //then
        then(repository).should().findAll(any(Predicate.class));
        assertNotNull(movimentacoesEncontradas, "Lista não deveria ser nula");
        assertEquals(2, movimentacoesEncontradas.size(), "Tamanho da lista diferente do esperado");
    }

    @DisplayName("Consultar Movimentação no ano/mes por inteiros")
    @Test
    void consultarMovimentacaoAnoMesPorValoresInteiros() {
        //given
        given(repository.findAll(any(Predicate.class))).willReturn(movimentacoes);

        //when
        ResponseConsultarMovimentacaoAnoMesDTO response = service.consultarMovimentacaoAnoMes(2019, 11,
                TipoMovimentacao.GASTO);

        //then
        then(repository).should().findAll(any(Predicate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertNotNull(response.getMovimentacoes(), "Lista não deveria ser nula");
        assertEquals(2, response.getMovimentacoes().size(), "Tamanho da lista diferente do esperado");
    }

    @DisplayName("Consultar Movimentação no ano/mes por inteiros -  Excecao Conversao Mês")
    @Test
    void consultarMovimentacaoAnoMesPorValoresInteiros_excecaoConversaoDataMes() {
        assertThrows(DateTimeException.class, () -> service.consultarMovimentacaoAnoMes(2019, 23,
                TipoMovimentacao.GASTO));
    }

    @DisplayName("Consultar Movimentação no ano/mes por inteiros -  Excecao Conversao Ano")
    @Test
    void consultarMovimentacaoAnoMesPorValoresInteiros_excecaoConversaoDataAno() {
        assertThrows(DateTimeException.class, () -> service.consultarMovimentacaoAnoMes(1231231231, 11,
                TipoMovimentacao.GASTO));
    }

    @DisplayName("Consultar Movimentações por categoria")
    @Test
    void consultarMovimentacaoCategoria() throws Exception {
        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(new Categoria());
        given(repository.findAll(any(Predicate.class))).willReturn(movimentacoes);

        //when
        ResponseConsultarMovimentacaoCategoriaDTO response = service.consultarMovimentacaoCategoria(1,
                2019,
                11);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().findAll(any(Predicate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertNotNull(response.getMovimentacoes(), "Lista não deveria ser nula");
        assertEquals(2, response.getMovimentacoes().size(), "Tamanho da lista diferente do esperado");
        assertEquals(new BigDecimal("400"), response.getValorTotal(), "Valor total diferente do esperado");
    }

    @DisplayName("Consultar Movimentações por categoria - Movimentacao Vazia")
    @Test
    void consultarMovimentacaoCategoria_movimentacaoVazia() throws Exception {
        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(new Categoria());
        given(repository.findAll(any(Predicate.class))).willReturn(new ArrayList());

        //when
        ResponseConsultarMovimentacaoCategoriaDTO response = service.consultarMovimentacaoCategoria(1,
                2019,
                11);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().findAll(any(Predicate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertNotNull(response.getMovimentacoes(), "Lista não deveria ser nula");
        assertEquals(0, response.getMovimentacoes().size(), "Tamanho da lista diferente do esperado");
        assertEquals(new BigDecimal("0"), response.getValorTotal(), "Valor total diferente do esperado");
    }

    @DisplayName("Consultar Movimentação no ano/mes por inteiros -  Excecao Conversao Data")
    @Test
    void consultarMovimentacaoCategoria_excecaoConversaoDataMes() {
        assertThrows(DateTimeException.class, () -> service.consultarMovimentacaoCategoria(1,
                2019,
                23));

        assertThrows(DateTimeException.class, () -> service.consultarMovimentacaoCategoria(1,
                1231231231,
                11));
    }
}