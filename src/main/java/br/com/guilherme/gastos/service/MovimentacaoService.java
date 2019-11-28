package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoCompativelException;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.exception.OrigemNaoCompativelException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.movimentacao.BuscarMovimentacaoService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.*;

@Service
@AllArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final BuscarCategoriaService buscarCategoriaService;

    private final BuscarOrigemService buscarOrigemService;

    private final BuscarMovimentacaoService buscarMovimentacaoService;

    private Categoria getCategoriaMovimentacao(Integer idCategoria, TipoMovimentacao tipoMovimentacao){
        Categoria categoria = buscarCategoriaService.buscar(idCategoria);

        if(categoria.getTipoMovimentacao() != tipoMovimentacao)
            throw new CategoriaNaoCompativelException();

        return categoria;
    }

    private Origem getOrigemMovimentacao(Integer idOrigem, TipoMovimentacao tipoMovimentacao){
        Origem origem = buscarOrigemService.buscar(idOrigem);

        if(origem.getTipoMovimentacao() != tipoMovimentacao)
            throw new OrigemNaoCompativelException();

        return origem;
    }

    @Transactional
    public Movimentacao alterarMovimentacao(Integer id, RequestAlterarMovimentacaoDTO request){

        Movimentacao movimentacao = buscarMovimentacaoService.buscarMovimentacao(id);

        movimentacao.setValor(request.getValor());
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), movimentacao.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), movimentacao.getTipoMovimentacao()));


        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void deletarMovimentacao(Integer id){

        Movimentacao movimentacao = buscarMovimentacaoService.buscarMovimentacao(id);

        movimentacaoRepository.delete(movimentacao);
    }

}
