package br.com.guilherme.gastos.service.movimentacao;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import br.com.guilherme.gastos.exception.MovimentacaoNaoEncontradaException;
import br.com.guilherme.gastos.repository.MovimentacaoRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarMovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final SessaoService sessaoService;

    public Movimentacao buscarMovimentacao(Integer id) throws MovimentacaoNaoEncontradaException {

        Optional<Movimentacao> gasto = movimentacaoRepository.findByIdAndUsuario(id, sessaoService.getUsuarioAtual());

        return gasto.orElseThrow(MovimentacaoNaoEncontradaException::new);
    }

    public MovimentacaoDTO buscarMovimentacaoDTO(Integer id) throws MovimentacaoNaoEncontradaException {

        return ModelMapper.getMapper().map(this.buscarMovimentacao(id), MovimentacaoDTO.class);
    }
}
