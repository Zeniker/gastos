package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoCompativelException;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import br.com.guilherme.gastos.utils.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlterarMovimentacaoService extends ManutencaoMovimentacao {

    private final BuscarMovimentacaoService buscarMovimentacaoService;

    private final MovimentacaoRepository movimentacaoRepository;

    public AlterarMovimentacaoService(BuscarOrigemService buscarOrigemService,
                                      BuscarCategoriaService buscarCategoriaService,
                                      BuscarMovimentacaoService buscarMovimentacaoService,
                                      MovimentacaoRepository movimentacaoRepository) {

        super(buscarOrigemService, buscarCategoriaService);
        this.buscarMovimentacaoService = buscarMovimentacaoService;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    private Movimentacao alterarMovimentacao(Movimentacao movimentacao){
        if(movimentacao.getId() == null){
            throw new IllegalArgumentException("Id não pode ser nulo ao alterar categoria");
        }

        return movimentacaoRepository.save(movimentacao);
    }

    public MovimentacaoDTO alterarMovimentacaoDTO(Integer id, RequestAlterarMovimentacaoDTO request)
            throws ServiceException {

        Movimentacao movimentacao = buscarMovimentacaoService.buscarMovimentacao(id);

        ModelMapper.getMapper().map(request, movimentacao);

        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), movimentacao.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), movimentacao.getTipoMovimentacao()));

        return ModelMapper.getMapper().map(alterarMovimentacao(movimentacao), MovimentacaoDTO.class);
    }
}
