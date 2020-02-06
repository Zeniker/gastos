package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.exception.ServiceException;
import br.com.guilherme.gastos.repository.OrigemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarOrigemService {

    private final OrigemRepository origemRepository;

    public Origem buscar(Integer id) throws OrigemNaoEncontradaException {

        Optional<Origem> optionalOrigem = origemRepository.findById(id);

        return optionalOrigem.orElseThrow(OrigemNaoEncontradaException::new);
    }

    public OrigemDTO buscarDTO(Integer id) throws OrigemNaoEncontradaException {

        return new OrigemDTO(this.buscar(id));
    }
}
