package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InserirMovimentacaoService extends ManutencaoMovimentacao {

    private final MovimentacaoRepository movimentacaoRepository;

    public InserirMovimentacaoService(BuscarOrigemService buscarOrigemService,
                                      BuscarCategoriaService buscarCategoriaService,
                                      MovimentacaoRepository movimentacaoRepository) {

        super(buscarOrigemService, buscarCategoriaService);
        this.movimentacaoRepository = movimentacaoRepository;
    }

    private Movimentacao inserir(Movimentacao movimentacao){

        movimentacao.setId(null);

        return movimentacaoRepository.save(movimentacao);
    }

    public MovimentacaoDTO inserirDTO(RequestInserirMovimentacaoDTO request) throws ServiceException {

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setValor(request.getValor());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setTipoMovimentacao(request.getTipoMovimentacao());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), request.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), request.getTipoMovimentacao()));

        return new MovimentacaoDTO(this.inserir(movimentacao));
    }
}
