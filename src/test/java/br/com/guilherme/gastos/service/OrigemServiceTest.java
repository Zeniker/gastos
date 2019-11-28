package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
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
class OrigemServiceTest {

    @Mock
    private OrigemRepository repository;

    @InjectMocks
    private OrigemService service;

    @Captor
    private ArgumentCaptor<Origem> captor;

    private Origem origem;

    @BeforeEach
    void setUp() {
        origem = new Origem();

    }

    @DisplayName("Listar Origem")
    @Test
    void listar() {

        //given
        given(repository.findAll()).willReturn(Arrays.asList(origem, origem));

        //when
        List<Origem> origensEncontradas = service.listar();

        //then
        then(repository).should().findAll();
        assertNotNull(origensEncontradas, "Lista não deveria ser nula");
        assertEquals(2, origensEncontradas.size(), "Tamanho da lista diferente do esperado");

    }

    @DisplayName("Buscar Origem")
    @Test
    void buscar() {

        //given
        origem.setNome("Teste");

        given(repository.findById(anyInt())).willReturn(Optional.of(origem));

        //when
        Origem origemEncontrada = service.buscar(1);

        //then
        then(repository).should().findById(anyInt());
        assertNotNull(origemEncontrada, "Objeto não deveria ser nulo");
        assertEquals("Teste", origemEncontrada.getNome(), "Nome diferente do esperado");

    }

    @DisplayName("Buscar Origem - Excecao Origem Nao Encontrada")
    @Test
    void buscar_excecaoOrigemNaoEncontrada() {

        //given
        given(repository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(OrigemNaoEncontradaException.class, () -> service.buscar(1));

        //then
        then(repository).should().findById(anyInt());

    }

    @DisplayName("Alterar Origem")
    @Test
    void alterar() {

        //given
        given(repository.save(captor.capture())).willReturn(origem);
        given(repository.findById(anyInt())).willReturn(Optional.of(origem));

        //when
        RequestAlterarOrigemDTO request = new RequestAlterarOrigemDTO();
        request.setNome("TesteAlterado");

        Origem origem = service.alterar(1, request);

        //then
        then(repository).should().save(any(Origem.class));
        assertNotNull(origem, "Objeto não deveria ser nulo");
        assertEquals("TesteAlterado", origem.getNome(), "Nome diferente do esperado");

    }

    @DisplayName("Deletar Origem")
    @Test
    void deletar() {

        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(origem));

        //when
        service.deletar(1);

        //then
        then(repository).should().findById(anyInt());
        then(repository).should().delete(any(Origem.class));

    }
}