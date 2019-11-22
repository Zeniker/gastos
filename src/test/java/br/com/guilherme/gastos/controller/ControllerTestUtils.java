package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControllerTestUtils<T extends ResponseDTO> {

    public void testaResponseEntityOk(ResponseEntity<T> responseEntity){
        assertNotNull(responseEntity, "ResponseEntity não deveria ser nulo");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HttpStatus diferente do esperado");
        assertNotNull(responseEntity.getBody(), "Corpo da resposta não deve ser nulo");
    }

    public void testaResponseEntityBadRequest(ResponseEntity<T> responseEntity, String mensagemErro){
        assertNotNull(responseEntity, "ResponseEntity não deveria ser nulo");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), "HttpStatus diferente do esperado");
        assertNotNull(responseEntity.getBody(), "Corpo da resposta não deve ser nulo");
        assertEquals(mensagemErro, responseEntity.getBody().getMensagemErro(),
                        "Mensagem de erro diferente do esperado");
    }

}
