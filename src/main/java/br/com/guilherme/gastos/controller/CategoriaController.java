package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseBuscarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseInserirCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseListarCategoriaDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.service.categoria.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categoria")
@AllArgsConstructor
@Slf4j
public class CategoriaController {

    private final InserirCategoriaService inserirCategoriaService;
    private final ListarCategoriaService listarCategoriaService;
    private final BuscarCategoriaService buscarCategoriaService;
    private final AlterarCategoriaService alterarCategoriaService;
    private final DeletarCategoriaService deletarCategoriaService;

    @PostMapping
    public ResponseEntity<ResponseInserirCategoriaDTO> inserirCategoria(
            @Valid @RequestBody RequestInserirCategoriaDTO request) throws ServiceException {

        return ResponseEntity.ok(
                        new ResponseInserirCategoriaDTO(inserirCategoriaService.inserirDTO(request))
        );
    }

    @GetMapping
    public ResponseEntity<ResponseListarCategoriaDTO> listarCategoria(){

        return ResponseEntity.ok(new ResponseListarCategoriaDTO(listarCategoriaService.listarDTO()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarCategoriaDTO> buscarCategoria(@PathVariable Integer id) throws ServiceException {

        return ResponseEntity.ok(
                        new ResponseBuscarCategoriaDTO(buscarCategoriaService.buscarDTO(id))
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarCategoriaDTO> alterarCategoria(@PathVariable Integer id,
                    @Valid @RequestBody RequestAlterarCategoriaDTO request) throws ServiceException {

        return ResponseEntity.ok(
                        new ResponseAlterarCategoriaDTO(alterarCategoriaService.alterarDTO(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarCategoria(@PathVariable Integer id) throws ServiceException {

        return ResponseEntity.ok(deletarCategoriaService.deletarDTO(id));

    }

}
