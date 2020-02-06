package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.*;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.movimentacao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MovimentacaoControllerTest {

    @Mock
    private InserirMovimentacaoService inserirMovimentacaoService;

    @Mock
    private ConsultarMovimentacaoService consultarMovimentacaoService;

    @Mock
    private BuscarMovimentacaoService buscarMovimentacaoService;

    @Mock
    private AlterarMovimentacaoService alterarMovimentacaoService;

    @Mock
    private DeletarMovimentacaoService deletarMovimentacaoService;

    @InjectMocks
    private MovimentacaoController controller;

    private MovimentacaoDTO movimentacaoDTO;

    @BeforeEach
    void setUp() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setCategoria(new Categoria());
        movimentacaoDTO = new MovimentacaoDTO(movimentacao);
    }

    @DisplayName("Inserir Movimentacao")
    @Test
    void inserirMovimentacao() throws Exception {
        //given
        given(inserirMovimentacaoService.inserirDTO(any(RequestInserirMovimentacaoDTO.class)))
                .willReturn(movimentacaoDTO);

        //when
        ResponseEntity<ResponseInserirMovimentacaoDTO> responseEntity =
                controller.inserirMovimentacao(new RequestInserirMovimentacaoDTO());

        //then
        then(inserirMovimentacaoService).should().inserirDTO(any(RequestInserirMovimentacaoDTO.class));
        ControllerTestUtils<ResponseInserirMovimentacaoDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);
    }

    @DisplayName("Inserir Movimentacao - BadRequest")
    @Test
    void inserirMovimentacao_badRequest() throws Exception {
        //given
        given(inserirMovimentacaoService.inserirDTO(any(RequestInserirMovimentacaoDTO.class)))
                .willThrow(new CategoriaNaoEncontradaException());

        //when
        assertThrows(ServiceException.class, () -> controller.inserirMovimentacao(new RequestInserirMovimentacaoDTO()));
    }

    @DisplayName("Consultar movimentação ano/mês")
    @Test
    void consultarMovimentacaoAnoMes() {

        //given
        given(consultarMovimentacaoService.consultarMovimentacaoAnoMes(anyInt(), anyInt()))
                .willReturn(new ResponseConsultarMovimentacaoAnoMesDTO(new ArrayList<>()));

        //when
        ResponseEntity<ResponseConsultarMovimentacaoAnoMesDTO> responseEntity =
                controller.consultarMovimentacaoAnoMes(1, 1);

        //then
        then(consultarMovimentacaoService).should().consultarMovimentacaoAnoMes(anyInt(), anyInt());
        ControllerTestUtils<ResponseConsultarMovimentacaoAnoMesDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);
    }

    @DisplayName("Consultar movimentação por categoria")
    @Test
    void consultarMovimentacaoCategoria() throws Exception {

        //given
        given(consultarMovimentacaoService.consultarMovimentacaoCategoria(anyInt(), anyInt(), anyInt()))
                .willReturn(
                        new ResponseConsultarMovimentacaoCategoriaDTO(new ArrayList<>(), new BigDecimal("200"))
                );

        //when
        ResponseEntity<ResponseConsultarMovimentacaoCategoriaDTO> responseEntity =
                controller.consultarMovimentacaoCategoria(1, 1, 1);

        //then
        then(consultarMovimentacaoService).should().consultarMovimentacaoCategoria(anyInt(), anyInt(), anyInt());
        ControllerTestUtils<ResponseConsultarMovimentacaoCategoriaDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);
    }

    @DisplayName("Consultar movimentação por categoria - BadRequest")
    @Test
    void consultarMovimentacaoCategoria_badRequest() throws Exception {

        //given
        given(consultarMovimentacaoService.consultarMovimentacaoCategoria(anyInt(), anyInt(), anyInt()))
                .willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseConsultarMovimentacaoCategoriaDTO> responseEntity =
                controller.consultarMovimentacaoCategoria(1, 1, 1);

        //then
        then(consultarMovimentacaoService).should().consultarMovimentacaoCategoria(anyInt(), anyInt(), anyInt());
        ControllerTestUtils<ResponseConsultarMovimentacaoCategoriaDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");
    }

    @DisplayName("Buscar Movimentação")
    @Test
    void buscarMovimentacao() throws Exception {

        //given
        given(buscarMovimentacaoService.buscarMovimentacaoDTO(anyInt())).willReturn(movimentacaoDTO);

        //when
        ResponseEntity<ResponseBuscarMovimentacaoDTO> responseEntity = controller.buscarMovimentacao(1);

        //then
        then(buscarMovimentacaoService).should().buscarMovimentacaoDTO(anyInt());
        ControllerTestUtils<ResponseBuscarMovimentacaoDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);
    }

    @DisplayName("Buscar Movimentação - BadRequest")
    @Test
    void buscarMovimentacao_badRequest() throws Exception {

        //given
        given(buscarMovimentacaoService.buscarMovimentacaoDTO(anyInt()))
                .willThrow(new MovimentacaoNaoEncontradaException());

        //when
        assertThrows(MovimentacaoNaoEncontradaException.class, () -> controller.buscarMovimentacao(1));
    }

    @DisplayName("Alterar Movimentação")
    @Test
    void alterarMovimentacao() throws Exception {

        //given
        given(alterarMovimentacaoService.alterarMovimentacaoDTO(anyInt(), any(RequestAlterarMovimentacaoDTO.class)))
                .willReturn(movimentacaoDTO);

        //when
        ResponseEntity<ResponseAlterarMovimentacaoDTO> responseEntity = controller.alterarMovimentacao(1,
                new RequestAlterarMovimentacaoDTO());

        //then
        then(alterarMovimentacaoService).should().alterarMovimentacaoDTO(anyInt(),
                any(RequestAlterarMovimentacaoDTO.class));

        ControllerTestUtils<ResponseAlterarMovimentacaoDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Alterar Movimentação - BadRequest")
    @Test
    void alterarMovimentacao_badRequest() throws Exception {

        //given
        given(alterarMovimentacaoService.alterarMovimentacaoDTO(anyInt(), any(RequestAlterarMovimentacaoDTO.class)))
                .willThrow(new MovimentacaoNaoEncontradaException());

        //when
        assertThrows(ServiceException.class,
                () -> controller.alterarMovimentacao(1, new RequestAlterarMovimentacaoDTO()));

    }

    @DisplayName("Deletar Movimentação")
    @Test
    void deletarMovimentacao() throws Exception {

        //given
        given(deletarMovimentacaoService.deletarMovimentacaoDTO(anyInt()))
                .willReturn(new ResponseDTO());

        //when
        ResponseEntity<ResponseDTO> responseEntity = controller.deletarMovimentacao(1);

        //then
        then(deletarMovimentacaoService).should().deletarMovimentacaoDTO(anyInt());
        ControllerTestUtils<ResponseDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);
    }

    @DisplayName("Deletar Movimentação - BadRequest")
    @Test
    void deletarMovimentacao_badRequest() throws Exception {

        //given
        given(deletarMovimentacaoService.deletarMovimentacaoDTO(anyInt()))
                .willThrow(new MovimentacaoNaoEncontradaException());

        //when
        assertThrows(MovimentacaoNaoEncontradaException.class, () -> controller.deletarMovimentacao(1));
    }
}