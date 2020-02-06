package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.request.RequestLoginDTO;
import br.com.guilherme.gastos.dto.sessao.request.RequestRegistrarDTO;
import br.com.guilherme.gastos.dto.sessao.response.ResponseLoginDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.sessao.RegistrarService;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("sessao")
@Slf4j
public class SessaoController {

    private final SessaoService sessaoService;
    private final RegistrarService registrarService;

    public SessaoController(SessaoService sessaoService, RegistrarService registrarService) {
        this.sessaoService = sessaoService;
        this.registrarService = registrarService;
    }

    @PostMapping("/autenticar")
    public ResponseLoginDTO autenticar(@RequestBody @Valid RequestLoginDTO requestDto){

        return sessaoService.autenticar(requestDto);

    }

    @PostMapping("/registrar")
    public void registrar(@RequestBody @Valid RequestRegistrarDTO requestDTO) throws ServiceException {

        registrarService.registrar(requestDTO);

    }

}


