package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BuscarMovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository repository;

    @InjectMocks
    private BuscarMovimentacaoService service;

    private Movimentacao movimentacao;

    @BeforeEach
    void setUp() {
        movimentacao = new Movimentacao();
        movimentacao.setId(1);
        movimentacao.setDescricao("Teste");
        movimentacao.setCategoria(new Categoria());
    }

    @DisplayName("Buscar Movimentação")
    @Test
    void buscarMovimentacao() throws Exception {
        //given
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

    @DisplayName("Buscar Movimentação DTO")
    @Test
    void buscarMovimentacaoDTO() throws Exception {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(movimentacao));

        //when
        MovimentacaoDTO movimentacaoEncontrada = service.buscarMovimentacaoDTO(1);

        //then
        then(repository).should().findById(anyInt());
        assertNotNull(movimentacaoEncontrada, "Movimentação não deveria ser nulo");
        assertEquals(Integer.valueOf(1), movimentacaoEncontrada.getId(), "ID diferente do esperado");
        assertEquals("Teste", movimentacaoEncontrada.getDescricao(), "Descrição diferente do esperado");
    }

}