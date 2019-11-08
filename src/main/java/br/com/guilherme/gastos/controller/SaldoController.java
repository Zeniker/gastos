package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.saldo.ResponseConsultarSaldoAnoMes;
import br.com.guilherme.gastos.service.SaldoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("saldo")
@RestController
@AllArgsConstructor
public class SaldoController {

    private SaldoService saldoService;

    @GetMapping
    public ResponseEntity<ResponseConsultarSaldoAnoMes> consultarSaldoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        try{
            return ResponseEntity.ok().body(saldoService.consultarSaldoAnoMes(ano, mes));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseConsultarSaldoAnoMes(e.getMessage()));
        }

    }

}
