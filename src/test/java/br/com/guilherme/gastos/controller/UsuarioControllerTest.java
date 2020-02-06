package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.EmailJaCadastradoException;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.usuario.InserirUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private InserirUsuarioService service;

    @InjectMocks
    private UsuarioController controller;

    @DisplayName("Registrar novo usuário")
    @Test
    void registrar() throws Exception {

        //when
        controller.registrar(new RequestRegistrarDTO());

        //then
        then(service).should().registrar(any(RequestRegistrarDTO.class));

    }

    @DisplayName("Registrar novo usuário - ServiceException ")
    @Test
    void registrar_serviceException() throws Exception {

        //when
        doThrow(new EmailJaCadastradoException()).when(service).registrar(any(RequestRegistrarDTO.class));

        //then
        assertThrows(ServiceException.class, () -> controller.registrar(new RequestRegistrarDTO()));

    }

}