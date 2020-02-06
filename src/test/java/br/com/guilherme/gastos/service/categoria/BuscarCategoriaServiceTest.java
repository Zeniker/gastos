package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class BuscarCategoriaServiceTest extends ServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private BuscarCategoriaService service;

    private Categoria categoria;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        categoria = new Categoria();

        categoria.setId(1);
        categoria.setTipoMovimentacao(TipoMovimentacao.GANHO);
        categoria.setDescricao("Teste");
    }

    @DisplayName("Buscar Categoria")
    @Test
    void buscar() throws Exception {
        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.of(categoria));

        //when
        Categoria categoriaEncontrada = service.buscar(1);

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));
        assertNotNull(categoriaEncontrada, "Categoria não deveria ser nula");
        assertEquals(1, categoriaEncontrada.getId().intValue(), "Id diferente do esperado");
        assertEquals("Teste", categoriaEncontrada.getDescricao(), "Descrição diferente do esperado");
        assertEquals(TipoMovimentacao.GANHO, categoriaEncontrada.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");
    }

    @DisplayName("Buscar Categoria - Excecao Categoria Nao Encontrada")
    @Test
    void buscar_excecaoCategoriaNaoEncontrada() {

        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.empty());

        //when
        assertThrows(CategoriaNaoEncontradaException.class, () -> service.buscar(1));

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));
    }

    @DisplayName("Buscar Categoria DTO")
    @Test
    void buscarDTO() throws Exception {
        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.of(categoria));

        //when
        CategoriaDTO categoriaEncontrada = service.buscarDTO(1);

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));
        assertNotNull(categoriaEncontrada, "Categoria não deveria ser nula");
        assertEquals(1, categoriaEncontrada.getId().intValue(), "Id diferente do esperado");
        assertEquals("Teste", categoriaEncontrada.getDescricao(), "Descrição diferente do esperado");
        assertEquals(TipoMovimentacao.GANHO, categoriaEncontrada.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");
    }

}