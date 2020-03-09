package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.TesteUnitario;
import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AlterarCategoriaServiceTest implements TesteUnitario {

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private AlterarCategoriaService service;

    @DisplayName("Alterar Categoria DTO")
    @Test
    void alterarDTO() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setTipoMovimentacao(TipoMovimentacao.GASTO);
        categoria.setDescricao("Antigo");

        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);
        given(repository.save(any(Categoria.class))).willReturn(categoria);

        //when
        RequestAlterarCategoriaDTO request = new RequestAlterarCategoriaDTO();
        request.setDescricao("Teste");

        CategoriaDTO categoriaAlterada = service.alterarDTO(1, request);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().save(categoria);
        assertNotNull(categoriaAlterada, "Categoria não deveria ser nula");
        assertEquals("Teste", categoriaAlterada.getDescricao(), "Descrição diferente do esperado");
        assertEquals(1, categoriaAlterada.getId().intValue(), "Id não deveria ter sido modificado");
        assertEquals(TipoMovimentacao.GASTO, categoriaAlterada.getTipoMovimentacao(), "Tipo movimentação não " +
                "deveria ter sido alterado");
    }

    @DisplayName("Alterar Categoria DTO - Erro id nulo")
    @Test
    void alterarDTO_erroIdNulo() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(null);

        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);

        //when
        assertThrows(IllegalArgumentException.class, () -> service.alterarDTO(1, new RequestAlterarCategoriaDTO()));

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
    }

}