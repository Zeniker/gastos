package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.request.RequestLoginDTO;
import br.com.guilherme.gastos.dto.sessao.response.ResponseLoginDTO;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SessaoControllerTest {

    @Mock
    private SessaoService service;

    @InjectMocks
    private SessaoController controller;

    @DisplayName("Autenticar usuário")
    @Test
    void autenticar() {

        //given
        var response = new ResponseLoginDTO("");
        given(service.autenticar(any(RequestLoginDTO.class))).willReturn(response);

        //when
        var responseRetornado = controller.autenticar(new RequestLoginDTO());

        //then
        then(service).should().autenticar(any(RequestLoginDTO.class));
        assertEquals(response, responseRetornado, "Response diferente do esperado");

    }
}