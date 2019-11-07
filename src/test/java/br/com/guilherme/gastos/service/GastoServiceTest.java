package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.gasto.request.RequestAlterarGastoDTO;
import br.com.guilherme.gastos.dto.gasto.request.RequestInserirGastoDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseConsultarGastoAnoMesDTO;
import br.com.guilherme.gastos.exception.GastoNaoEncontradoException;
import br.com.guilherme.gastos.repository.GastoRepository;
import com.querydsl.core.types.Predicate;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GastoServiceTest {

    @Mock
    private GastoRepository gastoRepository;

    @InjectMocks
    private GastoService gastoService;

    @Captor
    private ArgumentCaptor<Gasto> captor;

    private Gasto gasto;

    @BeforeEach
    void setUp() {
        gasto = new Gasto();
    }

    @DisplayName("Inserir gasto")
    @Test
    void inserirGasto() {
        //given
        given(gastoRepository.save(captor.capture())).willReturn(gasto);

        //when
        LocalDate data = LocalDate.now();
        RequestInserirGastoDTO request = new RequestInserirGastoDTO();
        request.setDescricao("Teste");
        request.setDataEntrada(data);
        request.setValor(new BigDecimal("150"));

        Gasto gastoSalvo = gastoService.inserirGasto(request);

        //then
        then(gastoRepository).should().save(any(Gasto.class));
        assertNotNull(gastoSalvo, "Gasto salvo não deveria ser nulo");
        assertEquals("Teste", captor.getValue().getDescricao(), "Descrição diferente do esperado");
        assertEquals(data, captor.getValue().getDataEntrada(), "Data de entrada diferente do esperado");
        assertEquals(new BigDecimal("150"), captor.getValue().getValor(), "Valor diferente do esperado");
    }

    @DisplayName("Consultar Gasto por ano/mes por LocalDate")
    @Test
    void consultarGastoAnoMesPorLocalDate() {
        //given
        Iterable gastos = Arrays.asList(gasto, gasto);

        given(gastoRepository.findAll(any(Predicate.class))).willReturn(gastos);

        //when
        List<Gasto> gastosEncontrados = gastoService.consultarGastoAnoMes(LocalDate.now());

        //then
        then(gastoRepository).should().findAll(any(Predicate.class));
        assertNotNull(gastosEncontrados, "Lista não deveria ser nula");
        assertEquals(2, gastosEncontrados.size(), "Tamanho da lista diferente do esperado");
    }

    @DisplayName("Consultar Gasto no ano/mes por inteiros")
    @Test
    void consultarGastoAnoMesPorValoresInteiros() {
        //given
        Iterable gastos = Arrays.asList(gasto, gasto);

        given(gastoRepository.findAll(any(Predicate.class))).willReturn(gastos);

        //when
        ResponseConsultarGastoAnoMesDTO response = gastoService.consultarGastoAnoMes(2019, 11);

        //then
        then(gastoRepository).should().findAll(any(Predicate.class));
        assertNotNull(response, "Response não deveria ser nulo");
        assertNotNull(response.getGastos(), "Lista não deveria ser nula");
        assertEquals(2, response.getGastos().size(), "Tamanho da lista diferente do esperado");
    }

    @DisplayName("Consultar Gasto no ano/mes por inteiros -  Excecao Conversao Mês")
    @Test
    void consultarGastoAnoMesPorValoresInteiros_excecaoConversaoDataMes() {
        assertThrows(DateTimeException.class, () -> gastoService.consultarGastoAnoMes(2019, 23));
    }

    @DisplayName("Consultar Gasto no ano/mes por inteiros -  Excecao Conversao Ano")
    @Test
    void consultarGastoAnoMesPorValoresInteiros_excecaoConversaoDataAno() {
        assertThrows(DateTimeException.class, () -> gastoService.consultarGastoAnoMes(1231231231, 11));
    }

    @DisplayName("Buscar Gasto")
    @Test
    void buscarGasto() {
        //given
        gasto.setId(1);
        gasto.setDescricao("Teste");

        given(gastoRepository.findById(anyInt())).willReturn(Optional.of(gasto));

        //when
        Gasto gastoEncontrado = gastoService.buscarGasto(1);

        //then
        then(gastoRepository).should().findById(anyInt());
        assertNotNull(gastoEncontrado, "Gasto não deveria ser nulo");
        assertEquals(Integer.valueOf(1), gasto.getId(), "ID diferente do esperado");
        assertEquals("Teste", gasto.getDescricao(), "Descrição diferente do esperado");
    }

    @DisplayName("Buscar Gasto - Excecao gasto não encontrado")
    @Test
    void buscarGasto_ExcecaoGastoNaoEncontrado() {
        //given
        given(gastoRepository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(GastoNaoEncontradoException.class, () -> gastoService.buscarGasto(1));
    }

    @Test
    void alterarGasto() {
        //given
        given(gastoRepository.findById(anyInt())).willReturn(Optional.of(gasto));
        given(gastoRepository.save(captor.capture())).willReturn(gasto);

        //when
        LocalDate data = LocalDate.now();
        RequestAlterarGastoDTO request = new RequestAlterarGastoDTO();
        request.setDataEntrada(data);
        request.setDescricao("Alterada");
        request.setValor(new BigDecimal("200"));

        Gasto gastoAlterado = gastoService.alterarGasto(1, request);

        //then
        then(gastoRepository).should().findById(anyInt());
        then(gastoRepository).should().save(any(Gasto.class));
        assertNotNull(gastoAlterado, "Gasto alterado não deveria ser nulo");
        assertEquals("Alterada", captor.getValue().getDescricao(), "Descrição diferente do esperado");
        assertEquals(new BigDecimal("200"), captor.getValue().getValor(), "Valor diferente do esperado");
        assertEquals(data, captor.getValue().getDataEntrada(), "Data diferente do esperado");
    }

    @Test
    void deletarGasto() {
        //given
        given(gastoRepository.findById(anyInt())).willReturn(Optional.of(gasto));

        //when
        gastoService.deletarGasto(1);

        //then
        then(gastoRepository).should().findById(anyInt());
        then(gastoRepository).should().delete(gasto);
    }
}