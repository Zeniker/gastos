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
import br.com.guilherme.gastos.service.origem.*;
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
    private InserirOrigemService inserirOrigemService;

    @Mock
    private BuscarOrigemService buscarOrigemService;

    @Mock
    private ListarOrigemService listarOrigemService;

    @Mock
    private AlterarOrigemService alterarOrigemService;

    @Mock
    private DeletarOrigemService deletarOrigemService;

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
        given(inserirOrigemService.inserirDTO(any(RequestInserirOrigemDTO.class)))
                .willThrow(new RuntimeException("Mensagem erro"));

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
        given(listarOrigemService.listarDTO()).willReturn(Collections.singletonList(origemDTO));

        //when
        ResponseEntity<ResponseListarOrigemDTO> responseEntity =
                        controller.listarOrigens();

        //then
        then(listarOrigemService).should().listarDTO();
        ControllerTestUtils<ResponseListarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Listar origens - BadRequest")
    @Test
    void listarOrigens_badRequest() {

        //given
        given(listarOrigemService.listarDTO()).willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseListarOrigemDTO> responseEntity =
                        controller.listarOrigens();

        //then
        then(listarOrigemService).should().listarDTO();
        ControllerTestUtils<ResponseListarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Buscar origem")
    @Test
    void buscarOrigem() {

        //given
        given(buscarOrigemService.buscarDTO(anyInt())).willReturn(origemDTO);

        //when
        ResponseEntity<ResponseBuscarOrigemDTO> responseEntity = controller.buscarOrigem(1);

        //then
        then(buscarOrigemService).should().buscarDTO(anyInt());
        ControllerTestUtils<ResponseBuscarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Buscar origem - BadRequest")
    @Test
    void buscarOrigem_badRequest() {

        //given
        given(buscarOrigemService.buscarDTO(anyInt())).willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseBuscarOrigemDTO> responseEntity = controller.buscarOrigem(1);

        //then
        then(buscarOrigemService).should().buscarDTO(anyInt());
        ControllerTestUtils<ResponseBuscarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Alterar origem")
    @Test
    void alterarOrigem() {

        //given
        given(alterarOrigemService.alterarDTO(anyInt(), any(RequestAlterarOrigemDTO.class))).willReturn(origemDTO);

        //when
        ResponseEntity<ResponseAlterarOrigemDTO> responseEntity = controller.alterarOrigem(1,
                        new RequestAlterarOrigemDTO());

        //then
        then(alterarOrigemService).should().alterarDTO(anyInt(), any(RequestAlterarOrigemDTO.class));
        ControllerTestUtils<ResponseAlterarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Alterar origem - BadRequest")
    @Test
    void alterarOrigem_badRequest() {

        //given
        given(alterarOrigemService.alterarDTO(anyInt(), any(RequestAlterarOrigemDTO.class)))
                        .willThrow(new RuntimeException("Mensagem erro"));

        //when
        ResponseEntity<ResponseAlterarOrigemDTO> responseEntity = controller.alterarOrigem(1,
                        new RequestAlterarOrigemDTO());

        //then
        then(alterarOrigemService).should().alterarDTO(anyInt(), any(RequestAlterarOrigemDTO.class));
        ControllerTestUtils<ResponseAlterarOrigemDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }

    @DisplayName("Deletar origem")
    @Test
    void deletarOrigem() {

        //given
        given(deletarOrigemService.deletarDTO(anyInt())).willReturn(new ResponseDTO());

        //when
        ResponseEntity<ResponseDTO> responseEntity = controller.deletarOrigem(1);

        //then
        then(deletarOrigemService).should().deletarDTO(anyInt());
        ControllerTestUtils<ResponseDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityOk(responseEntity);

    }

    @DisplayName("Deletar origem - BadRequest")
    @Test
    void deletarOrigem_badRequest() {

        //given
        doThrow(new RuntimeException("Mensagem erro")).when(deletarOrigemService).deletarDTO(anyInt());

        //when
        ResponseEntity<ResponseDTO> responseEntity = controller.deletarOrigem(1);

        //then
        then(deletarOrigemService).should().deletarDTO(anyInt());
        ControllerTestUtils<ResponseDTO> testUtils = new ControllerTestUtils<>();
        testUtils.testaResponseEntityBadRequest(responseEntity, "Mensagem erro");

    }
}