package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoCompativelException;
import br.com.guilherme.gastos.exception.OrigemNaoCompativelException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AlterarMovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @Mock
    private BuscarMovimentacaoService buscarMovimentacaoService;

    @InjectMocks
    private AlterarMovimentacaoService service;

    private Movimentacao movimentacao;

    private Categoria categoria;

    private Origem origem;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();
        categoria.setId(1);

        origem = new Origem();
        origem.setId(2);

        movimentacao = new Movimentacao();
        movimentacao.setId(3);
    }

    @DisplayName("Alterar movimentação")
    @Test
    void alterarMovimentacao() throws Exception {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GASTO);

        given(buscarCategoriaService.buscar(1)).willReturn(categoria);
        given(buscarOrigemService.buscar(2)).willReturn(origem);
        given(buscarMovimentacaoService.buscarMovimentacao(anyInt())).willReturn(movimentacao);
        given(repository.save(any(Movimentacao.class))).willReturn(movimentacao);

        //when
        LocalDate data = LocalDate.now();
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setDataEntrada(data);
        request.setDescricao("Alterada");
        request.setValor(new BigDecimal("200"));
        request.setCategoria(1);
        request.setOrigem(2);

        MovimentacaoDTO movimentacaoAlterada = service.alterarMovimentacaoDTO(1, request);

        //then
        then(buscarCategoriaService).should().buscar(1);
        then(buscarOrigemService).should().buscar(2);
        then(buscarMovimentacaoService).should().buscarMovimentacao(anyInt());
        then(repository).should().save(any(Movimentacao.class));
        assertNotNull(movimentacaoAlterada, "Movimentação alterado não deveria ser nulo");
        assertEquals("Alterada", movimentacaoAlterada.getDescricao(), "Descrição diferente do esperado");
        assertEquals(new BigDecimal("200"), movimentacaoAlterada.getValor(), "Valor diferente do esperado");
        assertEquals(data, movimentacaoAlterada.getDataEntrada(), "Data diferente do esperado");
        assertEquals(1, movimentacaoAlterada.getCategoria().intValue(), "Categoria diferente do esperado");
        assertEquals(2, movimentacaoAlterada.getOrigem().intValue(), "Origem diferente do esperado");
    }

    @DisplayName("Alterar movimentação - Categoria Não Compatível")
    @Test
    void alterarMovimentacao_erroCategoriaNaoCompativel() throws Exception {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(buscarMovimentacaoService.buscarMovimentacao(anyInt())).willReturn(movimentacao);

        //when
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setCategoria(1);

        assertThrows(CategoriaNaoCompativelException.class, () -> service.alterarMovimentacaoDTO(1, request));

        //then
        then(buscarMovimentacaoService).should().buscarMovimentacao(anyInt());
        then(buscarCategoriaService).should().buscar(anyInt());
    }

    @DisplayName("Alterar movimentação - Origem Não Compatível")
    @Test
    void alterarMovimentacao_erroOrigemNaoCompativel() throws Exception {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(buscarOrigemService.buscar(anyInt())).willReturn(origem);
        given(buscarMovimentacaoService.buscarMovimentacao(anyInt())).willReturn(movimentacao);

        //when
        RequestAlterarMovimentacaoDTO request = new RequestAlterarMovimentacaoDTO();
        request.setCategoria(1);
        request.setOrigem(2);

        assertThrows(OrigemNaoCompativelException.class, () -> service.alterarMovimentacaoDTO(1, request));

        //then
        then(buscarMovimentacaoService).should().buscarMovimentacao(anyInt());
        then(buscarCategoriaService).should().buscar(anyInt());
    }

}