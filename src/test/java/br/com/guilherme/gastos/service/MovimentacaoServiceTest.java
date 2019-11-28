package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoCompativelException;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.exception.OrigemNaoCompativelException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @InjectMocks
    private MovimentacaoService service;

    @Captor
    private ArgumentCaptor<Movimentacao> captor;

    private Movimentacao movimentacao;

    private Categoria categoria;

    private Origem origem;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();

        origem = new Origem();

        movimentacao = new Movimentacao();
        movimentacao.setCategoria(categoria);
    }

    @DisplayName("Inserir movimentação")
    @Test
    void inserirMovimentacao() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);

        given(repository.save(captor.capture())).willReturn(movimentacao);
        given(buscarCategoriaService.buscar(1)).willReturn(categoria);
        given(buscarOrigemService.buscar(2)).willReturn(origem);

        //when
        LocalDate data = LocalDate.now();
        RequestInserirMovimentacaoDTO request = new RequestInserirMovimentacaoDTO();
        request.setDescricao("Teste");
        request.setDataEntrada(data);
        request.setValor(new BigDecimal("150"));
        request.setCategoria(1);
        request.setOrigem(2);
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        Movimentacao movimentacaoSalva = service.inserirMovimentacao(request);

        //then
        then(repository).should().save(any(Movimentacao.class));
        then(buscarCategoriaService).should().buscar(1);
        then(buscarOrigemService).should().buscar(2);
        assertNotNull(movimentacaoSalva, "Movimentação salva não deveria ser nulo");
        assertEquals("Teste", captor.getValue().getDescricao(), "Descrição diferente do esperado");
        assertEquals(data, captor.getValue().getDataEntrada(), "Data de entrada diferente do esperado");
        assertEquals(new BigDecimal("150"), captor.getValue().getValor(), "Valor diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, captor.getValue().getTipoMovimentacao(),
                        "Tipo de movimentação diferente do esperado");
        assertEquals(categoria, captor.getValue().getCategoria(), "Categoria diferente do esperado");
        assertEquals(origem, captor.getValue().getOrigem(), "Origem diferente do esperado");

    }

    @DisplayName("Inserir movimentação - Categoria Não Compatível")
    @Test
    void inserirMovimentacao_erroCategoriaNaoCompativel() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);

        //when
        RequestInserirMovimentacaoDTO request = new RequestInserirMovimentacaoDTO();
        request.setCategoria(1);
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        assertThrows(CategoriaNaoCompativelException.class, () -> service.inserirMovimentacao(request));

        //then
        then(buscarCategoriaService).should().buscar(anyInt());

    }

    @DisplayName("Inserir movimentação - Origem Não Compatível")
    @Test
    void inserirMovimentacao_erroOrigemNaoCompativel() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        origem.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(buscarOrigemService.buscar(anyInt())).willReturn(origem);

        //when
        RequestInserirMovimentacaoDTO request = new RequestInserirMovimentacaoDTO();
        request.setCategoria(1);
        request.setOrigem(1);
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        assertThrows(OrigemNaoCompativelException.class, () -> service.inserirMovimentacao(request));

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(buscarOrigemService).should().buscar(anyInt());

    }

    @DisplayName("Consultar Movimentacao por ano/mes por LocalDate")
    @Test
    void consultarMovimentacaoAnoMesPorLocalDate() {
        //given
        Iterable movimentacoes = Arrays.asList(movimentacao, movimentacao);

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
        Iterable movimentacoes = Arrays.asList(movimentacao, movimentacao);

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

    @DisplayName("Buscar Movimentação")
    @Test
    void buscarMovimentacao() {
        //given
        movimentacao.setId(1);
        movimentacao.setDescricao("Teste");

        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));

        //when
        Movimentacao movimentacaoEncontrada = service.buscarMovimentacao(1);

        //then
        then(repository).should().findById(anyInt());
        assertNotNull(movimentacaoEncontrada, "Movimentação não deveria ser nulo");
        assertEquals(Integer.valueOf(1), movimentacaoEncontrada.getId(), "ID diferente do esperado");
        assertEquals("Teste", movimentacaoEncontrada.getDescricao(), "Descrição diferente do esperado");
    }

    @DisplayName("Buscar Movimentação - Excecao movimentação não encontrada")
    @Test
    void buscarMovimentacao_ExcecaoMovimentacaoNaoEncontrada() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(MovimentacaoNaoEncontradaException.class, () -> service.buscarMovimentacao(1));

        //then
        then(repository).should().findById(anyInt());
    }

    @DisplayName("Alterar movimentação")
    @Test
    void alterarMovimentacao() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GASTO);

        given(buscarCategoriaService.buscar(1)).willReturn(categoria);
        given(buscarOrigemService.buscar(2)).willReturn(origem);
        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));
        given(repository.save(captor.capture())).willReturn(movimentacao);

        //when
        LocalDate data = LocalDate.now();
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setDataEntrada(data);
        request.setDescricao("Alterada");
        request.setValor(new BigDecimal("200"));
        request.setCategoria(1);
        request.setOrigem(2);

        Movimentacao movimentacaoAlterada = service.alterarMovimentacao(1, request);

        //then
        then(buscarCategoriaService).should().buscar(1);
        then(buscarOrigemService).should().buscar(2);
        then(repository).should().findById(anyInt());
        then(repository).should().save(any(Movimentacao.class));
        assertNotNull(movimentacaoAlterada, "Movimentação alterado não deveria ser nulo");
        assertEquals("Alterada", captor.getValue().getDescricao(), "Descrição diferente do esperado");
        assertEquals(new BigDecimal("200"), captor.getValue().getValor(), "Valor diferente do esperado");
        assertEquals(data, captor.getValue().getDataEntrada(), "Data diferente do esperado");
    }

    @DisplayName("Alterar movimentação - Categoria Não Compatível")
    @Test
    void alterarMovimentacao_erroCategoriaNaoCompativel() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));

        //when
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setCategoria(1);

        assertThrows(CategoriaNaoCompativelException.class, () -> service.alterarMovimentacao(1, request));

        //then
        then(repository).should().findById(anyInt());
        then(buscarCategoriaService).should().buscar(anyInt());
    }

    @DisplayName("Alterar movimentação - Origem Não Compatível")
    @Test
    void alterarMovimentacao_erroOrigemNaoCompativel() {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(buscarOrigemService.buscar(anyInt())).willReturn(origem);
        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));

        //when
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setCategoria(1);
        request.setOrigem(2);

        assertThrows(OrigemNaoCompativelException.class, () -> service.alterarMovimentacao(1, request));

        //then
        then(repository).should().findById(anyInt());
        then(buscarCategoriaService).should().buscar(anyInt());
    }

    @DisplayName("Deletar movimentação")
    @Test
    void deletarMovimentacao() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));

        //when
        service.deletarMovimentacao(1);

        //then
        then(repository).should().findById(anyInt());
        then(repository).should().delete(movimentacao);
    }
}