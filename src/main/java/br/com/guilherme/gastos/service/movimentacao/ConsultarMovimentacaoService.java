package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoCategoriaDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.*;

@Service
public class ConsultarMovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final IterableToCollection<Movimentacao> iterableToCollection;

    private final BuscarCategoriaService buscarCategoriaService;

    public ConsultarMovimentacaoService(MovimentacaoRepository movimentacaoRepository,
                                        BuscarCategoriaService buscarCategoriaService) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.buscarCategoriaService = buscarCategoriaService;
        this.iterableToCollection = new IterableToCollection<>();
    }

    @SuppressWarnings("unchecked")
    List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate, TipoMovimentacao tipoMovimentacao){

        BooleanExpression booleanExpression = byDataEntradaMes(localDate)
                .and(byDataEntradaAno(localDate))
                .and(byTipoMovimentacao(tipoMovimentacao));

        return iterableToCollection.toList(movimentacaoRepository.findAll(booleanExpression));
    }

    @SuppressWarnings("unchecked")
    List<Movimentacao> consultarMovimentacaoCategoria(Categoria categoria, LocalDate localDate){

        BooleanExpression booleanExpression = byDataEntradaMes(localDate)
                .and(byDataEntradaAno(localDate))
                .and(byCategoria(categoria));

        return iterableToCollection.toList(movimentacaoRepository.findAll(booleanExpression));
    }

    public List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate){

        return this.consultarMovimentacaoAnoMes(localDate, null);
    }

    private List<MovimentacaoDTO> converteMovimentacaoParaDto(List<Movimentacao> movimentacoes){
        return movimentacoes.stream().map(MovimentacaoDTO::new)
                .collect(Collectors.toList());
    }

    ResponseConsultarMovimentacaoAnoMesDTO consultarMovimentacaoAnoMes(Integer ano, Integer mes,
                                                                       TipoMovimentacao tipoMovimentacao){

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        List<Movimentacao> listMovimentacao = this.consultarMovimentacaoAnoMes(dataConsulta, tipoMovimentacao);

        List<MovimentacaoDTO> listMovimentacaoDTO = converteMovimentacaoParaDto(listMovimentacao);

        return new ResponseConsultarMovimentacaoAnoMesDTO(listMovimentacaoDTO);
    }

    public ResponseConsultarMovimentacaoAnoMesDTO consultarMovimentacaoAnoMes(Integer ano, Integer mes){
        return this.consultarMovimentacaoAnoMes(ano, mes, null);
    }

    /**
     * Consulta e retorna um dto com a lista de movimentações de uma determinada categoria
     * e com a soma do valor de todas as movimentações listadas
     *
     * @param idCategoria Id da categoria a ser utilizada
     * @param ano Ano para consultar as movimentações
     * @param mes Mês para consutlar as movimentações
     * @return ResponseConsultarMovimentacaoCategoriaDTO com valor total e lista de movimentações
     */
    public ResponseConsultarMovimentacaoCategoriaDTO consultarMovimentacaoCategoria(Integer idCategoria, Integer ano,
                                                                                    Integer mes)
            throws CategoriaNaoEncontradaException {

        LocalDate dataConsulta = LocalDate.of(ano, mes, 1);

        Categoria categoria = buscarCategoriaService.buscar(idCategoria);

        List<Movimentacao> movimentacoes = consultarMovimentacaoCategoria(categoria, dataConsulta);

        BigDecimal valorTotal = movimentacoes.stream()
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResponseConsultarMovimentacaoCategoriaDTO(converteMovimentacaoParaDto(movimentacoes), valorTotal);
    }
}
