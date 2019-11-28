package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseBuscarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseInserirMovimentacaoDTO;
import br.com.guilherme.gastos.service.MovimentacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {

        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<ResponseInserirMovimentacaoDTO> inserirMovimentacao(
                    @RequestBody @Valid RequestInserirMovimentacaoDTO requestInserirMovimentacaoDTO){
        try{
            ResponseInserirMovimentacaoDTO responseDTO = new ResponseInserirMovimentacaoDTO(
                            movimentacaoService.inserirMovimentacao(requestInserirMovimentacaoDTO)
            );

            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao inserir movimentacao", e);
            return ResponseEntity.badRequest().body(new ResponseInserirMovimentacaoDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseConsultarMovimentacaoAnoMesDTO> consultarMovimentacaoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        try{
            ResponseConsultarMovimentacaoAnoMesDTO responseDTO = movimentacaoService.consultarMovimentacaoAnoMes(ano, mes);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao consultar movimentacao no ano/mês", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarMovimentacaoAnoMesDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarMovimentacaoDTO> buscarMovimentacao(@PathVariable Integer id){
        try{
            ResponseBuscarMovimentacaoDTO responseDTO = new ResponseBuscarMovimentacaoDTO(
                            movimentacaoService.buscarMovimentacao(id)
            );
            return ResponseEntity.ok().body(responseDTO);
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
                            movimentacaoService.alterarMovimentacao(id, requestAlterarMovimentacaoDTO)
            );

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e){
            log.error("Erro ao alterar movimentacao", e);
            return ResponseEntity.badRequest().body(new ResponseAlterarMovimentacaoDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMovimentacao(@PathVariable Integer id){
        try{
            movimentacaoService.deletarMovimentacao(id);

            return ResponseEntity.ok().body(new ResponseDTO());
        } catch (Exception e){
            log.error("Erro ao deletar movimentacao", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
