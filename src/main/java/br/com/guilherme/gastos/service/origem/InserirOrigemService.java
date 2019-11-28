package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class InserirOrigemService {

    private OrigemRepository origemRepository;

    private Origem inserir(Origem origem) {
        origem.setId(null);

        return origemRepository.save(origem);
    }

    public OrigemDTO inserirDTO(RequestInserirOrigemDTO request) {

        Origem origem = new Origem();
        origem.setNome(request.getNome());
        origem.setTipoMovimentacao(request.getTipoMovimentacao());

        return new OrigemDTO(this.inserir(origem));
    }
}
