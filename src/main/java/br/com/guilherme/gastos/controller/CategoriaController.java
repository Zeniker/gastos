package br.com.guilherme.gastos.controller;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseBuscarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseInserirCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.response.ResponseListarCategoriaDTO;
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

    private InserirCategoriaService inserirCategoriaService;
    private ListarCategoriaService listarCategoriaService;
    private BuscarCategoriaService buscarCategoriaService;
    private AlterarCategoriaService alterarCategoriaService;
    private DeletarCategoriaService deletarCategoriaService;

    @PostMapping
    public ResponseEntity<ResponseInserirCategoriaDTO> inserirCategoria(
                    @Valid @RequestBody RequestInserirCategoriaDTO request){

        try{
            return ResponseEntity.ok(
                            new ResponseInserirCategoriaDTO(inserirCategoriaService.inserirDTO(request))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseInserirCategoriaDTO(e.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<ResponseListarCategoriaDTO> listarCategoria(){

        try{
            return ResponseEntity.ok(new ResponseListarCategoriaDTO(listarCategoriaService.listarDTO()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseListarCategoriaDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuscarCategoriaDTO> buscarCategoria(@PathVariable Integer id){

        try{
            return ResponseEntity.ok(
                            new ResponseBuscarCategoriaDTO(buscarCategoriaService.buscarDTO(id))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseBuscarCategoriaDTO(e.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlterarCategoriaDTO> alterarCategoria(@PathVariable Integer id,
                    @Valid @RequestBody RequestAlterarCategoriaDTO request){

        try{
            return ResponseEntity.ok(
                            new ResponseAlterarCategoriaDTO(alterarCategoriaService.alterarDTO(id, request))
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseAlterarCategoriaDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletarCategoria(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(deletarCategoriaService.deletarDTO(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }

}
