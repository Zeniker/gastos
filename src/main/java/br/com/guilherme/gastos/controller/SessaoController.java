package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.sessao.request.RequestLoginDTO;
import br.com.guilherme.gastos.dto.sessao.response.ResponseLoginDTO;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("sessao")
@Slf4j
@AllArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping("/autenticar")
    public ResponseLoginDTO autenticar(@RequestBody @Valid RequestLoginDTO requestDto){

        return sessaoService.autenticar(requestDto);

    }

}


