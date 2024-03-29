package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestInserirMovimentacaoDTO;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import br.com.guilherme.gastos.utils.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InserirMovimentacaoService extends ManutencaoMovimentacao {

    private final MovimentacaoRepository movimentacaoRepository;
    private final SessaoService sessaoService;

    public InserirMovimentacaoService(BuscarOrigemService buscarOrigemService,
                                      BuscarCategoriaService buscarCategoriaService,
                                      MovimentacaoRepository movimentacaoRepository,
                                      SessaoService sessaoService) {

        super(buscarOrigemService, buscarCategoriaService);
        this.movimentacaoRepository = movimentacaoRepository;
        this.sessaoService = sessaoService;
    }

    private Movimentacao inserir(Movimentacao movimentacao){

        movimentacao.setId(null);

        return movimentacaoRepository.save(movimentacao);
    }

    public MovimentacaoDTO inserirDTO(RequestInserirMovimentacaoDTO request) throws ServiceException {

        Movimentacao movimentacao = ModelMapper.getMapper().map(request, Movimentacao.class);
        movimentacao.setUsuario(sessaoService.getUsuarioAtual());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), request.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), request.getTipoMovimentacao()));

        return ModelMapper.getMapper().map(this.inserir(movimentacao), MovimentacaoDTO.class);
    }
}
