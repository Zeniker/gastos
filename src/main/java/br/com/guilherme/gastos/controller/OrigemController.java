package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseBuscarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseListarOrigemDTO;
import br.com.guilherme.gastos.service.OrigemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "origem")
@AllArgsConstructor
@Slf4j
public class OrigemController {

    private OrigemService origemService;

    @PostMapping
    public ResponseEntity<ResponseInserirOrigemDTO> inserirOrigem(
                    @Valid @RequestBody RequestInserirOrigemDTO request) {

        try{
            return ResponseEntity.ok(
                            new ResponseInserirOrigemDTO(origemService.inserir(request))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseInserirOrigemDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseListarOrigemDTO> listarOrigens() {

        try{
            return ResponseEntity.ok(
                            new ResponseListarOrigemDTO(origemService.listar())
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseListarOrigemDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarOrigemDTO> buscarOrigem(@PathVariable Integer id) {

        try{
            return ResponseEntity.ok(
                            new ResponseBuscarOrigemDTO(origemService.buscar(id))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseBuscarOrigemDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")

    public ResponseEntity<ResponseAlterarOrigemDTO> alterarOrigem(@PathVariable Integer id,
                    @Valid @RequestBody RequestAlterarOrigemDTO request) {

        try{
            return ResponseEntity.ok(
                            new ResponseAlterarOrigemDTO(origemService.alterar(id, request))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseAlterarOrigemDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarOrigem(@PathVariable Integer id) {

        try{
            origemService.deletar(id);
            return ResponseEntity.ok(new ResponseDTO());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}