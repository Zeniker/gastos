package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class InserirCategoriaServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private InserirCategoriaService service;

    @Captor
    private ArgumentCaptor<Categoria> captor;

    @DisplayName("Inserir Categoria a partir de DTO")
    @Test
    void inserirDTO() {

        //given
        Categoria categoria = new Categoria();
        given(repository.save(captor.capture())).willReturn(categoria);

        //when
        RequestInserirCategoriaDTO request = new RequestInserirCategoriaDTO();
        request.setDescricao("Teste");
        request.setTipoMovimentacao(TipoMovimentacao.GASTO);

        CategoriaDTO categoriaSalva = service.inserirDTO(request);

        //then
        then(repository).should().save(any(Categoria.class));
        assertNotNull(categoriaSalva, "Categoria salva não deveria ser nula");
        assertNull(captor.getValue().getId(), "Id diferente do esperado");
        assertEquals("Teste", captor.getValue().getDescricao(), "Descrição está diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, captor.getValue().getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");
    }
}