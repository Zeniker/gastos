package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseBuscarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseListarOrigemDTO;
import br.com.guilherme.gastos.service.OrigemService;
import br.com.guilherme.gastos.service.origem.InserirOrigemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class OrigemControllerTest {

    @Mock
    private OrigemService service;

    @Mock
    private InserirOrigemService inserirOrigemService;

    @InjectMocks
    private OrigemController controller;

    private OrigemDTO origemDTO;

    @BeforeEach
    void setUp() {
        origemDTO = new OrigemDTO(new Origem());
    }

    @DisplayName("Inserir Origem")
    @Test
    void inserirOrigem() {

        //given
        given(inserirOrigemService.inserirDTO(any(RequestInserirOrigemDTO.class))).willReturn(origemDTO);

        //when
        ResponseEntity<ResponseInserirOrigemDTO> responseEntity =
                        controller.inserirOrigem(new RequestInserirOrigemDTO());

        //then
        then(inserirOrigemService).should().inserirDTO(any(RequestInserirOrigemDTO.class));
        ControllerTestUtils<ResponseInserirOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Inserir origem - BadRequest")
    @Test
    void inserirOrigem_badRequest() {

        //given
        given(inserirOrigemService.inserirDTO(any(RequestInserirOrigemDTO.class))).willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseInserirOrigemDTO> responseEntity =
                        controller.inserirOrigem(new RequestInserirOrigemDTO());

        //then
        then(inserirOrigemService).should().inserirDTO(any(RequestInserirOrigemDTO.class));
        ControllerTestUtils<ResponseInserirOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Listar origens")
    @Test
    void listarOrigens() {

        //given
        given(service.listar()).willReturn(Collections.singletonList(new Origem()));

        //when
        ResponseEntity<ResponseListarOrigemDTO> responseEntity =
                        controller.listarOrigens();

        //then
        then(service).should().listar();
        ControllerTestUtils<ResponseListarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Listar origens - BadRequest")
    @Test
    void listarOrigens_badRequest() {

        //given
        given(service.listar()).willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseListarOrigemDTO> responseEntity =
                        controller.listarOrigens();

        //then
        then(service).should().listar();
        ControllerTestUtils<ResponseListarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Buscar origem")
    @Test
    void buscarOrigem() {

        //given
        given(service.buscar(anyInt())).willReturn(new Origem());

        //when
        ResponseEntity<ResponseBuscarOrigemDTO> responseEntity = controller.buscarOrigem(1);

        //then
        then(service).should().buscar(anyInt());
        ControllerTestUtils<ResponseBuscarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Buscar origem - BadRequest")
    @Test
    void buscarOrigem_badRequest() {

        //given
        given(service.buscar(anyInt())).willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseBuscarOrigemDTO> responseEntity = controller.buscarOrigem(1);

        //then
        then(service).should().buscar(anyInt());
        ControllerTestUtils<ResponseBuscarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Alterar origem")
    @Test
    void alterarOrigem() {

        //given
        given(service.alterar(anyInt(), any(RequestAlterarOrigemDTO.class))).willReturn(new Origem());

        //when
        ResponseEntity<ResponseAlterarOrigemDTO> responseEntity = controller.alterarOrigem(1,
                        new RequestAlterarOrigemDTO());

        //then
        then(service).should().alterar(anyInt(), any(RequestAlterarOrigemDTO.class));
        ControllerTestUtils<ResponseAlterarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Alterar origem - BadRequest")
    @Test
    void alterarOrigem_badRequest() {

        //given
        given(service.alterar(anyInt(), any(RequestAlterarOrigemDTO.class)))
                        .willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseAlterarOrigemDTO> responseEntity = controller.alterarOrigem(1,
                        new RequestAlterarOrigemDTO());

        //then
        then(service).should().alterar(anyInt(), any(RequestAlterarOrigemDTO.class));
        ControllerTestUtils<ResponseAlterarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Deletar origem")
    @Test
    void deletarOrigem() {

        //when
        ResponseEntity<ResponseDTO> responseEntity = controller.deletarOrigem(1);

        //then
        then(service).should().deletar(anyInt());
        ControllerTestUtils<ResponseDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Deletar origem - BadRequest")
    @Test
    void deletarOrigem_badRequest() {

        //given
        doThrow(new RuntimeException("Mensagem erro")).when(service).deletar(anyInt());

        //when
        ResponseEntity<ResponseDTO> responseEntity = controller.deletarOrigem(1);

        //then
        then(service).should().deletar(anyInt());
        ControllerTestUtils<ResponseDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }
}