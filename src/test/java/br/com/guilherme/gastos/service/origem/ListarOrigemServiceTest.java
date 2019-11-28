package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
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
class ListarOrigemServiceTest {

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private ListarOrigemService service;

    @DisplayName("Listar Origem DTO")
    @Test
    void listarDTO() {

        //given
        Origem origem = new Origem();
        given(repository.findAll()).willReturn(Arrays.asList(origem, origem));

        //when
        List<OrigemDTO> origensEncontradas = service.listarDTO();

        //then
        then(repository).should().findAll();
        assertNotNull(origensEncontradas, "Lista não deveria ser nula");
        assertEquals(2, origensEncontradas.size(), "Tamanho da lista diferente do esperado");

    }

}