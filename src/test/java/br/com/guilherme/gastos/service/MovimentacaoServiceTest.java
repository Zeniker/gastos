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
import br.com.guilherme.gastos.service.movimentacao.BuscarMovimentacaoService;
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

    @Mock
    private BuscarMovimentacaoService buscarMovimentacaoService;

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

    @DisplayName("Deletar movimentação")
    @Test
    void deletarMovimentacao() {
        //given
        given(buscarMovimentacaoService.buscarMovimentacao(anyInt())).willReturn(movimentacao);

        //when
        service.deletarMovimentacao(1);

        //then
        then(buscarMovimentacaoService).should().buscarMovimentacao(anyInt());
        then(repository).should().delete(movimentacao);
    }
}