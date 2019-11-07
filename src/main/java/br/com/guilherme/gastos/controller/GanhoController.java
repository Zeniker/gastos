package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.ganho.request.RequestAlterarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.request.RequestInserirGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseAlterarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseConsultarGanhoAnoMesDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseConsultarGanhoDTO;
import br.com.guilherme.gastos.dto.ganho.response.ResponseInserirGanhoDTO;
import br.com.guilherme.gastos.service.GanhoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("ganho")
@Slf4j
public class GanhoController {

    private GanhoService ganhoService;

    public GanhoController(GanhoService ganhoService) {

        this.ganhoService = ganhoService;
    }

    @PostMapping
    public ResponseEntity<ResponseInserirGanhoDTO> inserirGanho(@RequestBody @Valid RequestInserirGanhoDTO requestInserirGanhoDTO){
        try{
            ResponseInserirGanhoDTO responseDTO = new ResponseInserirGanhoDTO(
                            ganhoService.inserirGanho(requestInserirGanhoDTO)
            );

            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao inserir ganho", e);
            return ResponseEntity.badRequest().body(new ResponseInserirGanhoDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseConsultarGanhoAnoMesDTO> consultarGanhoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        try{
            ResponseConsultarGanhoAnoMesDTO responseDTO = ganhoService.consultarGanhoAnoMes(ano, mes);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao consultar ganhos no ano/mês", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarGanhoAnoMesDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseConsultarGanhoDTO> buscarGanho(@PathVariable Integer id){
        try{
            ResponseConsultarGanhoDTO responseDTO = new ResponseConsultarGanhoDTO(ganhoService.buscarGanho(id));
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e){
            log.error("Erro ao consultar ganho", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarGanhoDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarGanhoDTO> alterarGanho(@PathVariable Integer id,
                    @RequestBody @Valid RequestAlterarGanhoDTO requestAlterarGanhoDTO){
        try{
            ResponseAlterarGanhoDTO responseDTO = new ResponseAlterarGanhoDTO(
                            ganhoService.alterarGanho(id, requestAlterarGanhoDTO)
            );

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e){
            log.error("Erro ao alterar ganho", e);
            return ResponseEntity.badRequest().body(new ResponseAlterarGanhoDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarGanho(@PathVariable Integer id){
        try{
            ganhoService.deletarGanho(id);

            return ResponseEntity.ok().body(new ResponseDTO());
        } catch (Exception e){
            log.error("Erro ao deletar ganho", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
