package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.Predicate;
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

    private IterableToCollection<Movimentacao> iterableToCollection;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.iterableToCollection = new IterableToCollection<>();
    }

    public Movimentacao inserirMovimentacao(RequestInserirMovimentacaoDTO request){

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setValor(request.getValor());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setTipoMovimentacao(request.getTipoMovimentacao());

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate, TipoMovimentacao tipoMovimentacao){

        BooleanExpression booleanExpression = byDataEntradaMes(localDate)
                        .and(byDataEntradaAno(localDate));

        if(tipoMovimentacao != null){
            booleanExpression.and(byTipoMovimentacao(tipoMovimentacao));
        }

        return iterableToCollection.toList(movimentacaoRepository.findAll(booleanExpression));
    }

    public List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate){

        return this.consultarMovimentacaoAnoMes(localDate, null);
    }

    public ResponseConsultarMovimentacaoAnoMesDTO consultarMovimentacaoAnoMes(Integer ano, Integer mes,
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

        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void deletarMovimentacao(Integer id){

        Movimentacao movimentacao = buscarMovimentacao(id);

        movimentacaoRepository.delete(movimentacao);
    }

}
