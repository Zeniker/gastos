package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseAlterarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseBuscarOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseInserirOrigemDTO;
import br.com.guilherme.gastos.dto.origem.response.ResponseListarOrigemDTO;
import br.com.guilherme.gastos.service.origem.*;
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

    private final InserirOrigemService inserirOrigemService;
    private final BuscarOrigemService buscarOrigemService;
    private final ListarOrigemService listarOrigemService;
    private final AlterarOrigemService alterarOrigemService;
    private final DeletarOrigemService deletarOrigemService;

    @PostMapping
    public ResponseEntity<ResponseInserirOrigemDTO> inserirOrigem(
                    @Valid @RequestBody RequestInserirOrigemDTO request) {

        return ResponseEntity.ok(
                        new ResponseInserirOrigemDTO(inserirOrigemService.inserirDTO(request))
        );

    }

    @GetMapping
    public ResponseEntity<ResponseListarOrigemDTO> listarOrigens() {


        return ResponseEntity.ok(
                        new ResponseListarOrigemDTO(listarOrigemService.listarDTO())
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarOrigemDTO> buscarOrigem(@PathVariable Integer id) {


        return ResponseEntity.ok(
                        new ResponseBuscarOrigemDTO(buscarOrigemService.buscarDTO(id))
        );

    }

    @PutMapping("/{id}")

    public ResponseEntity<ResponseAlterarOrigemDTO> alterarOrigem(@PathVariable Integer id,
                    @Valid @RequestBody RequestAlterarOrigemDTO request) {


        return ResponseEntity.ok(
                        new ResponseAlterarOrigemDTO(alterarOrigemService.alterarDTO(id, request))
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarOrigem(@PathVariable Integer id) {

        return ResponseEntity.ok(deletarOrigemService.deletarDTO(id));

    }
}
