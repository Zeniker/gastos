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
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.*;

@Service
public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;

    private CategoriaService categoriaService;

    private OrigemService origemService;

    private IterableToCollection<Movimentacao> iterableToCollection;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, CategoriaService categoriaService,
                    OrigemService origemService) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.categoriaService = categoriaService;
        this.origemService = origemService;
        this.iterableToCollection = new IterableToCollection<>();
    }

    private Categoria getCategoriaMovimentacao(Integer idCategoria, TipoMovimentacao tipoMovimentacao){
        Categoria categoria = categoriaService.buscar(idCategoria);

        if(categoria.getTipoMovimentacao() != tipoMovimentacao)
            throw new CategoriaNaoCompativelException();

        return categoria;
    }

    private Origem getOrigemMovimentacao(Integer idOrigem, TipoMovimentacao tipoMovimentacao){
        Origem origem = origemService.buscar(idOrigem);

        if(origem.getTipoMovimentacao() != tipoMovimentacao)
            throw new OrigemNaoCompativelException();

        return origem;
    }

    @Transactional
    public Movimentacao inserirMovimentacao(RequestInserirMovimentacaoDTO request){

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setValor(request.getValor());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setTipoMovimentacao(request.getTipoMovimentacao());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), request.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), request.getTipoMovimentacao()));

        return movimentacaoRepository.save(movimentacao);
    }

    List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate, TipoMovimentacao tipoMovimentacao){

        BooleanExpression booleanExpression = byDataEntradaMes(localDate)
                        .and(byDataEntradaAno(localDate))
                        .and(byTipoMovimentacao(tipoMovimentacao));

        return iterableToCollection.toList(movimentacaoRepository.findAll(booleanExpression));
    }

    List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate){

        return this.consultarMovimentacaoAnoMes(localDate, null);
    }

    ResponseConsultarMovimentacaoAnoMesDTO consultarMovimentacaoAnoMes(Integer ano, Integer mes,
                    TipoMovimentacao tipoMovimentacao){

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Movimentacao> listMovimentacao = this.consultarMovimentacaoAnoMes(dataConsulta, tipoMovimentacao);

        List<MovimentacaoDTO> listMovimentacaoDTO = listMovimentacao.stream().map(MovimentacaoDTO::new)
                        .collect(Collectors.toList());

        return new ResponseConsultarMovimentacaoAnoMesDTO(listMovimentacaoDTO);
    }

    public ResponseConsultarMovimentacaoAnoMesDTO consultarMovimentacaoAnoMes(Integer ano, Integer mes){
        return this.consultarMovimentacaoAnoMes(ano, mes, null);
    }

    public Movimentacao buscarMovimentacao(Integer id){

        Optional<Movimentacao> gasto = movimentacaoRepository.findById(id);

        return gasto.orElseThrow(MovimentacaoNaoEncontradaException::new);
    }

    @Transactional
    public Movimentacao alterarMovimentacao(Integer id, RequestAlterarMovimentacaoDTO request){

        Movimentacao movimentacao = buscarMovimentacao(id);

        movimentacao.setValor(request.getValor());
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), movimentacao.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), movimentacao.getTipoMovimentacao()));


        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void deletarMovimentacao(Integer id){

        Movimentacao movimentacao = buscarMovimentacao(id);

        movimentacaoRepository.delete(movimentacao);
    }

}
