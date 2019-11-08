package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaService service;

    @Captor
    private ArgumentCaptor<Categoria> captor;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();
    }

    @DisplayName("Inserir Categoria")
    @Test
    void inserir() {

        //given
        given(repository.save(captor.capture())).willReturn(categoria);

        //when
        RequestInserirCategoriaDTO request = new RequestInserirCategoriaDTO();
        request.setDescricao("Teste");
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        Categoria categoriaSalva = service.inserir(request);

        //then
        then(repository).should().save(any(Categoria.class));
        assertNotNull(categoriaSalva, "Categoria salva não deveria ser nula");
        assertEquals("Teste", captor.getValue().getDescricao(), "Descrição está diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, captor.getValue().getTipoMovimentacao(),
                        "Tipo de movimentação diferente do esperado");
    }

    @DisplayName("Buscar Categoria")
    @Test
    void buscar() {
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);
        categoria.setDescricao("Teste");

        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(categoria));

        //when
        Categoria categoriaEncontrada = service.buscar(1);

        //then
        then(repository).should().findById(anyInt());
        assertNotNull(categoriaEncontrada, "Categoria não deveria ser nula");
        assertEquals("Teste", categoriaEncontrada.getDescricao(), "Descrição diferente do esperado");
        assertEquals(TipoMovimentacao.GANHO, categoriaEncontrada.getTipoMovimentacao(),
                        "Tipo de movimentação diferente do esperado");
    }

    @DisplayName("Buscar Categoria - Excecao Categoria Nao Encontrada")
    @Test
    void buscar_excecaoCategoriaNaoEncontrada() {

        //given
        given(repository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(CategoriaNaoEncontradaException.class, () -> service.buscar(1));
    }

    @DisplayName("Alterar Categoria")
    @Test
    void alterar() {

        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(categoria));
        given(repository.save(any(Categoria.class))).willReturn(categoria);

        //when
        RequestAlterarCategoriaDTO request = new RequestAlterarCategoriaDTO();
        request.setDescricao("Teste");

        Categoria categoriaAlterada = service.alterar(1, request);

        //then
        then(repository).should().findById(anyInt());
        then(repository).should().save(categoria);
        assertNotNull(categoriaAlterada, "Categoria não deveria ser nula");
        assertEquals("Teste", categoriaAlterada.getDescricao(), "Descrição diferente do esperado");
    }

    @DisplayName("Deletar Categoria")
    @Test
    void deletar() {

        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(categoria));

        //when
        service.deletar(1);

        //then
        then(repository).should().findById(anyInt());
        then(repository).should().delete(categoria);
    }
}