package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseBuscarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseInserirMovimentacaoDTO;
import br.com.guilherme.gastos.service.movimentacao.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("movimentacao")
@Slf4j
@AllArgsConstructor
public class MovimentacaoController {

    private final InserirMovimentacaoService inserirMovimentacaoService;
    private final ConsultarMovimentacaoService consultarMovimentacaoService;
    private final BuscarMovimentacaoService buscarMovimentacaoService;
    private final AlterarMovimentacaoService alterarMovimentacaoService;
    private final DeletarMovimentacaoService deletarMovimentacaoService;

    @PostMapping
    public ResponseEntity<ResponseInserirMovimentacaoDTO> inserirMovimentacao(
                    @RequestBody @Valid RequestInserirMovimentacaoDTO requestInserirMovimentacaoDTO){

        ResponseInserirMovimentacaoDTO responseDTO = new ResponseInserirMovimentacaoDTO(
                inserirMovimentacaoService.inserirDTO(requestInserirMovimentacaoDTO)
        );

        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping
    public ResponseEntity<ResponseConsultarMovimentacaoAnoMesDTO> consultarMovimentacaoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        return ResponseEntity.ok(consultarMovimentacaoService.consultarMovimentacaoAnoMes(ano, mes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarMovimentacaoDTO> buscarMovimentacao(@PathVariable Integer id){
        ResponseBuscarMovimentacaoDTO responseDTO = new ResponseBuscarMovimentacaoDTO(
                buscarMovimentacaoService.buscarMovimentacaoDTO(id)
        );
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarMovimentacaoDTO> alterarMovimentacao(@PathVariable Integer id,
                    @RequestBody @Valid RequestAlterarMovimentacaoDTO requestAlterarMovimentacaoDTO){

        ResponseAlterarMovimentacaoDTO responseDTO = new ResponseAlterarMovimentacaoDTO(
                alterarMovimentacaoService.alterarMovimentacaoDTO(id, requestAlterarMovimentacaoDTO)
        );

        return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarMovimentacao(@PathVariable Integer id){

        return ResponseEntity.ok(deletarMovimentacaoService.deletarMovimentacaoDTO(id));
    }

}
