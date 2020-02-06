package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DeletarMovimentacaoService {

    private BuscarMovimentacaoService buscarMovimentacaoService;

    private MovimentacaoRepository movimentacaoRepository;

    private void deletarMovimentacao(Integer id) throws MovimentacaoNaoEncontradaException {

        Movimentacao movimentacao = buscarMovimentacaoService.buscarMovimentacao(id);

        movimentacaoRepository.delete(movimentacao);
    }

    public ResponseDTO deletarMovimentacaoDTO(Integer id) throws MovimentacaoNaoEncontradaException {

        deletarMovimentacao(id);

        return new ResponseDTO();
    }
}
