package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestAlterarOrigemDTO;
import br.com.guilherme.gastos.exception.OrigemNaoEncontradaException;
import br.com.guilherme.gastos.repository.OrigemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AlterarOrigemService {

    private final OrigemRepository origemRepository;

    private BuscarOrigemService buscarOrigemService;

    private Origem alterar(Origem origem) {
        if(origem.getId() == null){
            throw new IllegalArgumentException("Id não pode ser nulo ao alterar origem");
        }

        return origemRepository.save(origem);
    }

    public OrigemDTO alterarDTO(Integer id, RequestAlterarOrigemDTO request) throws OrigemNaoEncontradaException {

        Origem origem = buscarOrigemService.buscar(id);
        origem.setNome(request.getNome());

        return new OrigemDTO(alterar(origem));
    }
}
