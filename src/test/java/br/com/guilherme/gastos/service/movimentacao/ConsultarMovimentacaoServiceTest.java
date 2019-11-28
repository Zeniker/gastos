package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ConsultarMovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @InjectMocks
    private ConsultarMovimentacaoService service;

    private Iterable movimentacoes;

    @BeforeEach
    void setUp() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setCategoria(new Categoria());

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

}