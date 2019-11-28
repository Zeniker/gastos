package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.dto.movimentacao.request.RequestAlterarMovimentacaoDTO;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import br.com.guilherme.gastos.service.origem.BuscarOrigemService;
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

    public Movimentacao alterarMovimentacao(Movimentacao movimentacao){
        if(movimentacao.getId() == null){
            throw new IllegalArgumentException("Id não pode ser nulo ao alterar categoria");
        }

        return movimentacaoRepository.save(movimentacao);
    }

    public MovimentacaoDTO alterarMovimentacaoDTO(Integer id, RequestAlterarMovimentacaoDTO request){

        Movimentacao movimentacao = buscarMovimentacaoService.buscarMovimentacao(id);

        movimentacao.setValor(request.getValor());
        movimentacao.setDataEntrada(request.getDataEntrada());
        movimentacao.setDescricao(request.getDescricao());
        movimentacao.setCategoria(getCategoriaMovimentacao(request.getCategoria(), movimentacao.getTipoMovimentacao()));
        movimentacao.setOrigem(getOrigemMovimentacao(request.getOrigem(), movimentacao.getTipoMovimentacao()));


        return new MovimentacaoDTO(alterarMovimentacao(movimentacao));
    }
}
