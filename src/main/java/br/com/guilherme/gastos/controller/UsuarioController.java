package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.usuario.InserirUsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
@Slf4j
@AllArgsConstructor
public class UsuarioController {

    private final InserirUsuarioService inserirUsuarioService;

    @PostMapping
    public void registrar(@RequestBody @Valid RequestRegistrarDTO requestDTO) throws ServiceException {

        inserirUsuarioService.registrar(requestDTO);

    }
}
