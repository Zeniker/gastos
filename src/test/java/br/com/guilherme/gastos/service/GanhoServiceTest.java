package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.dto.ganho.request.RequestAlterarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.request.RequestInserirGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseConsultarGanhoAnoMesDTO;
import br.com.guilherme.gastos.exception.GanhoNaoEncontradoException;
import br.com.guilherme.gastos.repository.GanhoRepository;
import com.querydsl.core.types.Predicate;
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
class GanhoServiceTest {

    @Mock
    private GanhoRepository ganhoRepository;

    @InjectMocks
    private GanhoService ganhoService;

    @Captor
    private ArgumentCaptor<Ganho> captor;

    private Ganho ganho;

    @BeforeEach
    void setUp() {
        ganho = new Ganho();
    }

    @Test
    @DisplayName("Inserir ganho")
    void inserirGanho() {
        //given
        given(ganhoRepository.save(captor.capture())).willReturn(ganho);

        //when
        LocalDate dataEntrada = LocalDate.now();

        RequestInserirGanhoDTO request = new RequestInserirGanhoDTO();
        request.setDataEntrada(dataEntrada);
        request.setValor(new BigDecimal("150"));
        request.setDescricao("Descricao");

        Ganho ganhoSalvo = ganhoService.inserirGanho(request);

        //then
        then(ganhoRepository).should().save(any(Ganho.class));
        assertNotNull(ganhoSalvo, "Ganho salvo não deveria ser nulo");
        assertEquals("Descricao", captor.getValue().getDescricao(), "Descricao diferente do esperado");
        assertEquals(dataEntrada, captor.getValue().getDataEntrada(), "Data de entrada diferente do esperado");
        assertEquals(new BigDecimal("150"), captor.getValue().getValor(), "Valor diferente do esperado");
    }

    @Test
    @DisplayName("Consultar Ganhos no ano/mês por LocalDate")
    void consultarGanhoAnoMesPorLocalDate() {
        //given
        Iterable iterable = Arrays.asList(ganho, ganho);

        given(ganhoRepository.findAll(any(Predicate.class))).willReturn(iterable);

        //when
        List<Ganho> ganhosAnoMes = ganhoService.consultarGanhoAnoMes(LocalDate.now());

        //then
        then(ganhoRepository).should().findAll(any(Predicate.class));
        assertNotNull(ganhosAnoMes, "Lista não deveria estar nula");
        assertEquals(2, ganhosAnoMes.size(), "Tamanho da lista diferente do esperado");
    }

    @Test
    @DisplayName("Consultar Ganhos no ano/mês por inteiros")
    void consultarGanhoAnoMesPorValoresInteiros() {
        //given
        Iterable iterable = Arrays.asList(ganho, ganho);

        given(ganhoRepository.findAll(any(Predicate.class))).willReturn(iterable);

        //when
        ResponseConsultarGanhoAnoMesDTO reponseDTO = ganhoService.consultarGanhoAnoMes(1995, 12);

        //then
        then(ganhoRepository).should().findAll(any(Predicate.class));
        assertNotNull(reponseDTO, "responseDTO não deveria estar nulo");
        assertNotNull(reponseDTO.getGanhos(), "Lista não deveria estar nula");
        assertEquals(2, reponseDTO.getGanhos().size(), "Tamanho da lista diferente do esperado");

    }

    @Test
    @DisplayName("Consultar Ganhos no ano/mês por inteiros - Excecao Conversao Mês")
    void consultarGanhoAnoMesPorValoresInteiros_excecaoConversaoDataMes() {
        assertThrows(DateTimeException.class, () -> ganhoService.consultarGanhoAnoMes(1995, 15));
    }

    @Test
    @DisplayName("Consultar Ganhos no ano/mês por inteiros - Excecao Conversao Ano")
    void consultarGanhoAnoMesPorValoresInteiros_excecaoConversaoDataAno() {
        assertThrows(DateTimeException.class, () -> ganhoService.consultarGanhoAnoMes(1203812931, 11));
    }

    @Test
    @DisplayName("Buscar ganho")
    void buscarGanho() {
        //given
        ganho.setId(1);
        ganho.setDescricao("Teste");

        given(ganhoRepository.findById(anyInt())).willReturn(Optional.of(ganho));

        //when
        Ganho ganhoEncontrado = ganhoService.buscarGanho(1);

        //then
        then(ganhoRepository).should().findById(anyInt());
        assertNotNull(ganhoEncontrado, "Ganho encontrado não pode ser nulo");
        assertEquals(Integer.valueOf(1), ganhoEncontrado.getId(), "Id diferente do esperado");
        assertEquals("Teste", ganhoEncontrado.getDescricao(), "Descrição diferente do esperado");

    }

    @Test
    @DisplayName("Buscar ganho - Excecao ganho não encontrado")
    void buscarGanho_excecaoGanhoNaoEncontrado() {
        //given
        given(ganhoRepository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(GanhoNaoEncontradoException.class, () -> ganhoService.buscarGanho(1));

        //then
        then(ganhoRepository).should().findById(anyInt());

    }

    @Test
    @DisplayName("Alterar ganho")
    void alterarGanho() {
        //given
        given(ganhoRepository.findById(anyInt())).willReturn(Optional.of(ganho));
        given(ganhoRepository.save(captor.capture())).willReturn(ganho);

        //when
        LocalDate data = LocalDate.now();
        RequestAlterarGanhoDTO request = new RequestAlterarGanhoDTO();
        request.setDataEntrada(data);
        request.setDescricao("Alterada");
        request.setValor(new BigDecimal("200"));

        Ganho ganhoAlterado = ganhoService.alterarGanho(1, request);

        //then
        then(ganhoRepository).should().findById(anyInt());
        then(ganhoRepository).should().save(any(Ganho.class));
        assertNotNull(ganhoAlterado, "Ganho alterado não deveria ser nulo");
        assertEquals("Alterada", captor.getValue().getDescricao(), "Descrição diferente do esperado");
        assertEquals(new BigDecimal("200"), captor.getValue().getValor(), "Valor diferente do esperado");
        assertEquals(data, captor.getValue().getDataEntrada(), "Data diferente do esperado");

    }

    @Test
    @DisplayName("Deletar ganho")
    void deletarGanho() {
        //given
        given(ganhoRepository.findById(anyInt())).willReturn(Optional.of(ganho));

        //when
        ganhoService.deletarGanho(1);

        //then
        then(ganhoRepository).should().findById(anyInt());
        then(ganhoRepository).should().delete(ganho);

    }
}