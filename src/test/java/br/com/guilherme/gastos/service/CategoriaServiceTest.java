package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
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
}