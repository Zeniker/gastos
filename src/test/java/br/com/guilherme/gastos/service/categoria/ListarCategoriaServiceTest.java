package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ListarCategoriaServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private ListarCategoriaService service;

    @DisplayName("Listar Categorias DTO")
    @Test
    void listarDTO() {

        //given
        Categoria categoria = new Categoria();
        given(repository.findAll()).willReturn(Arrays.asList(categoria, categoria));

        //when
        List<CategoriaDTO> categorias = service.listarDTO();

        //then
        then(repository).should().findAll();
        assertNotNull(categorias);
        assertEquals(2, categorias.size(), "Tamanho da lista diferente do esperado");
    }
}