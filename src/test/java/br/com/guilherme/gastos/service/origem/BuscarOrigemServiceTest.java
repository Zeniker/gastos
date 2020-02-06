package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
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
class BuscarOrigemServiceTest extends ServiceTest {

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private BuscarOrigemService service;

    private Origem origem;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        origem = new Origem();
        origem.setId(1);
        origem.setNome("Teste");
        origem.setTipoMovimentacao(TipoMovimentacao.GASTO);
    }

    @DisplayName("Buscar Origem")
    @Test
    void buscar() throws Exception {

        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.of(origem));

        //when
        Origem origemEncontrada = service.buscar(1);

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));
        assertNotNull(origemEncontrada, "Objeto não deveria ser nulo");
        assertEquals(1, origemEncontrada.getId().intValue(), "Id diferente do esperado");
        assertEquals("Teste", origemEncontrada.getNome(), "Nome diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, origemEncontrada.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");

    }

    @DisplayName("Buscar Origem - Excecao Origem Nao Encontrada")
    @Test
    void buscar_excecaoOrigemNaoEncontrada() {

        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.empty());

        //when
        assertThrows(OrigemNaoEncontradaException.class, () -> service.buscar(1));

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));

    }

    @DisplayName("Buscar Origem DTO")
    @Test
    void buscarDTO() throws Exception {

        //given
        given(repository.findByIdAndUsuario(anyInt(), any(Usuario.class))).willReturn(Optional.of(origem));

        //when
        OrigemDTO origemEncontrada = service.buscarDTO(1);

        //then
        then(repository).should().findByIdAndUsuario(anyInt(), any(Usuario.class));
        assertNotNull(origemEncontrada, "Objeto não deveria ser nulo");
        assertEquals(1, origemEncontrada.getId().intValue(), "Id diferente do esperado");
        assertEquals("Teste", origemEncontrada.getNome(), "Nome diferente do esperado");
        assertEquals(TipoMovimentacao.GASTO, origemEncontrada.getTipoMovimentacao(),
                "Tipo de movimentação diferente do esperado");

    }
}