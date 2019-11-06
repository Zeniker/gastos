package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.gasto.request.RequestAlterarGastoDTO;
import br.com.guilherme.gastos.dto.gasto.request.RequestInserirGastoDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseAlterarGastoDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseConsultarGastoDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseConsultarGastoAnoMesDTO;
import br.com.guilherme.gastos.dto.gasto.response.ResponseInserirGastoDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.GastoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("gasto")
@AllArgsConstructor
@Slf4j
public class GastoController {

    private GastoService gastoService;

    @PostMapping
    public ResponseEntity<ResponseInserirGastoDTO> inserirGasto(@RequestBody @Valid RequestInserirGastoDTO requestInserirGastoDTO){
        try{
            ResponseInserirGastoDTO responseDTO = new ResponseInserirGastoDTO(
                            gastoService.inserirGasto(requestInserirGastoDTO)
            );
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao inserir gasto", e);
            return ResponseEntity.badRequest().body(new ResponseInserirGastoDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseConsultarGastoAnoMesDTO> consultarGastoAnoMes(@RequestParam Integer ano,
                    @RequestParam Integer mes){
        try{
            ResponseConsultarGastoAnoMesDTO responseDTO = gastoService.consultarGastoAnoMes(ano, mes);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.error("Erro ao consultar gastos no ano/mês", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarGastoAnoMesDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseConsultarGastoDTO> consultarGasto(@PathVariable Integer id){
        try{
            ResponseConsultarGastoDTO responseDTO = new ResponseConsultarGastoDTO(
                            gastoService.consultarGasto(id)
            );

            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e) {
            log.error("Erro ao consultar gasto", e);
            return ResponseEntity.badRequest().body(new ResponseConsultarGastoDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarGastoDTO> alterarGasto(@PathVariable Integer id,
                    @RequestBody @Valid RequestAlterarGastoDTO requestAlterarGastoDTO){
        try{
            ResponseAlterarGastoDTO responseDTO = new ResponseAlterarGastoDTO(
                            gastoService.alterarGasto(id, requestAlterarGastoDTO)
            );

            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e) {
            log.error("Erro ao alterar gasto", e);
            return ResponseEntity.badRequest().body(new ResponseAlterarGastoDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarGasto(@PathVariable Integer id){
        try{
            gastoService.deletarGasto(id);

            return ResponseEntity.ok().body(new ResponseDTO());
        }catch (Exception e) {
            log.error("Erro ao deletar gasto", e);
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
