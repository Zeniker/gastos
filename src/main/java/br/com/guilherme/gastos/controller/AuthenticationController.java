package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.RequestLoginDTO;
import br.com.guilherme.gastos.dto.sessao.ResponseLoginDTO;
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
public class AuthenticationController {

    private final SessaoService sessaoService;

    public AuthenticationController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/login")
    public ResponseLoginDTO login(@RequestBody @Valid RequestLoginDTO requestDto){

        return sessaoService.login(requestDto);

    }

}
