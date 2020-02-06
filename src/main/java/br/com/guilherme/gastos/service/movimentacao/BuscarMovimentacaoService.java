package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarMovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public Movimentacao buscarMovimentacao(Integer id) throws MovimentacaoNaoEncontradaException {

        Optional<Movimentacao> gasto = movimentacaoRepository.findById(id);

        return gasto.orElseThrow(MovimentacaoNaoEncontradaException::new);
    }

    public MovimentacaoDTO buscarMovimentacaoDTO(Integer id) throws MovimentacaoNaoEncontradaException {

        return new MovimentacaoDTO(buscarMovimentacao(id));
    }
}
