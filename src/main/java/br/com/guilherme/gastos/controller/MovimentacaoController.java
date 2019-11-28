package br.com.guilherme.gastos.controller;

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
        try{
            ResponseInserirMovimentacaoDTO responseDTO = new ResponseInserirMovimentacaoDTO(
                    inserirMovimentacaoService.inserirMovimentacaoDTO(requestInserirMovimentacaoDTO)
            );

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            log.error("Erro ao inserir movimentacao", e);
            return ResponseEntity.badRequest().body(new ResponseInserirMovimentacaoDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseConsultarMovimentacaoAnoMesDTO> consultarMovimentacaoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        try{
            return ResponseEntity.ok(consultarMovimentacaoService.consultarMovimentacaoAnoMes(ano, mes));
        }catch (Exception e){
            log.error("Erro ao consultar movimentacao no ano/mês", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarMovimentacaoAnoMesDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarMovimentacaoDTO> buscarMovimentacao(@PathVariable Integer id){
        try{
            ResponseBuscarMovimentacaoDTO responseDTO = new ResponseBuscarMovimentacaoDTO(
                    buscarMovimentacaoService.buscarMovimentacaoDTO(id)
            );
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e){
            log.error("Erro ao consultar movimentacao", e);
            return ResponseEntity.badRequest().body(new ResponseBuscarMovimentacaoDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarMovimentacaoDTO> alterarMovimentacao(@PathVariable Integer id,
                    @RequestBody @Valid RequestAlterarMovimentacaoDTO requestAlterarMovimentacaoDTO){
        try{
            ResponseAlterarMovimentacaoDTO responseDTO = new ResponseAlterarMovimentacaoDTO(
                    alterarMovimentacaoService.alterarMovimentacaoDTO(id, requestAlterarMovimentacaoDTO)
            );

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e){
            log.error("Erro ao alterar movimentacao", e);
            return ResponseEntity.badRequest().body(new ResponseAlterarMovimentacaoDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMovimentacao(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(deletarMovimentacaoService.deletarMovimentacaoDTO(id));
        } catch (Exception e){
            log.error("Erro ao deletar movimentacao", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
