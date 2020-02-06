package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ListarOrigemServiceTest extends ServiceTest {

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private ListarOrigemService service;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    @DisplayName("Listar Origem DTO")
    @Test
    void listarDTO() {

        //given
        Origem origem = new Origem();
        given(repository.findByUsuario(any(Usuario.class))).willReturn(Arrays.asList(origem, origem));

        //when
        List<OrigemDTO> origensEncontradas = service.listarDTO();

        //then
        then(repository).should().findByUsuario(any(Usuario.class));
        assertNotNull(origensEncontradas, "Lista não deveria ser nula");
        assertEquals(2, origensEncontradas.size(), "Tamanho da lista diferente do esperado");

    }

}