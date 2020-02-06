package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoCompativelException;
import br.com.guilherme.gastos.exception.OrigemNaoCompativelException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.ServiceTest;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class InserirMovimentacaoServiceTest extends ServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @InjectMocks
    private InserirMovimentacaoService service;

    @Captor
    private ArgumentCaptor<Movimentacao> captor;

    private Movimentacao movimentacao;

    private Categoria categoria;

    private Origem origem;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        categoria = new Categoria();

        origem = new Origem();

        movimentacao = new Movimentacao();
        movimentacao.setCategoria(categoria);
    }

    @DisplayName("Inserir movimentação")
    @Test
    void inserirMovimentacao() throws Exception {
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

        MovimentacaoDTO movimentacaoSalva = service.inserirDTO(request);

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
    void inserirMovimentacao_erroCategoriaNaoCompativel() throws Exception {
        //given
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);

        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);

        //when
        RequestInserirMovimentacaoDTO request = new RequestInserirMovimentacaoDTO();
        request.setCategoria(1);
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        assertThrows(CategoriaNaoCompativelException.class, () -> service.inserirDTO(request));

        //then
        then(buscarCategoriaService).should().buscar(anyInt());

    }

    @DisplayName("Inserir movimentação - Origem Não Compatível")
    @Test
    void inserirMovimentacao_erroOrigemNaoCompativel() throws Exception {
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

        assertThrows(OrigemNaoCompativelException.class, () -> service.inserirDTO(request));

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(buscarOrigemService).should().buscar(anyInt());

    }
}