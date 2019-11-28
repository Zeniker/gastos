package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
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

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @InjectMocks
    private CategoriaService service;

    @Captor
    private ArgumentCaptor<Categoria> captor;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();
    }

    @DisplayName("Alterar Categoria")
    @Test
    void alterar() {

        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(repository.save(any(Categoria.class))).willReturn(categoria);

        //when
        RequestAlterarCategoriaDTO request = new RequestAlterarCategoriaDTO();
        request.setDescricao("Teste");

        Categoria categoriaAlterada = service.alterar(1, request);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().save(categoria);
        assertNotNull(categoriaAlterada, "Categoria não deveria ser nula");
        assertEquals("Teste", categoriaAlterada.getDescricao(), "Descrição diferente do esperado");
    }

    @DisplayName("Deletar Categoria")
    @Test
    void deletar() {

        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);

        //when
        service.deletar(1);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().delete(categoria);
    }
}