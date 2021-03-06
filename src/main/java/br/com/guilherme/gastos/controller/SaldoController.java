package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import br.com.guilherme.gastos.service.saldo.SaldoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("saldo")
@RestController
@AllArgsConstructor
@Slf4j
public class SaldoController {

    private final SaldoService saldoService;

    @GetMapping
    public ResponseEntity<ResponseConsultarSaldoAnoMes> consultarSaldoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){

        return ResponseEntity.ok().body(saldoService.consultarSaldoAnoMes(ano, mes));

    }

}
