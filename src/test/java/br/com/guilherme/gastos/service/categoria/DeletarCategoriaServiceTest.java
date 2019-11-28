package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DeletarCategoriaServiceTest {

    @Mock
    private BuscarCategoriaService buscarCategoriaService;

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private DeletarCategoriaService service;

    @DisplayName("Deletar Categoria DTO")
    @Test
    void deletarDTO() {
        Categoria categoria = new Categoria();

        //given
        given(buscarCategoriaService.buscar(anyInt())).willReturn(categoria);

        //when
        ResponseDTO responseDTO = service.deletarDTO(1);

        //then
        then(buscarCategoriaService).should().buscar(anyInt());
        then(repository).should().delete(categoria);
        assertNotNull(responseDTO, "ResponseDTO não deveria ser nulo");
    }

}