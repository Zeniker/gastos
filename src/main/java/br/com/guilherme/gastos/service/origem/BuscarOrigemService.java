package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarOrigemService {

    private final OrigemRepository origemRepository;
    private final SessaoService sessaoService;

    public Origem buscar(Integer id) throws OrigemNaoEncontradaException {

        Optional<Origem> optionalOrigem = origemRepository.findByIdAndUsuario(id, sessaoService.getUsuarioAtual());

        return optionalOrigem.orElseThrow(OrigemNaoEncontradaException::new);
    }

    public OrigemDTO buscarDTO(Integer id) throws OrigemNaoEncontradaException {

        return ModelMapper.getMapper().map(this.buscar(id), OrigemDTO.class);
    }
}
