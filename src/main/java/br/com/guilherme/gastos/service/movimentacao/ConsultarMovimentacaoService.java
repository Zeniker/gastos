package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.response.ResponseConsultarMovimentacaoAnoMesDTO;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import br.com.guilherme.gastos.utils.IterableToCollection;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.byDataEntradaAno;
import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.byDataEntradaMes;
import static br.com.guilherme.gastos.specification.MovimentacaoSpecification.byTipoMovimentacao;

@Service
public class ConsultarMovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final IterableToCollection<Movimentacao> iterableToCollection;

    public ConsultarMovimentacaoService(MovimentacaoRepository movimentacaoRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.iterableToCollection = new IterableToCollection<>();
    }

    @SuppressWarnings("unchecked")
    List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate, TipoMovimentacao tipoMovimentacao){

        BooleanExpression booleanExpression = byDataEntradaMes(localDate)
                .and(byDataEntradaAno(localDate))
                .and(byTipoMovimentacao(tipoMovimentacao));

        return iterableToCollection.toList(movimentacaoRepository.findAll(booleanExpression));
    }

    public List<Movimentacao> consultarMovimentacaoAnoMes(LocalDate localDate){

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
}
