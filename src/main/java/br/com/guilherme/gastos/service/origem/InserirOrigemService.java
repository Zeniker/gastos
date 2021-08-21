package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.dto.origem.request.RequestInserirOrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class InserirOrigemService {

    private final OrigemRepository origemRepository;
    private final SessaoService sessaoService;

    private Origem inserir(Origem origem) {
        origem.setId(null);

        return origemRepository.save(origem);
    }

    public OrigemDTO inserirDTO(RequestInserirOrigemDTO request) {

        Origem origem = ModelMapper.getMapper().map(request, Origem.class);
        origem.setNome(request.getNome());
        origem.setTipoMovimentacao(request.getTipoMovimentacao());
        origem.setUsuario(sessaoService.getUsuarioAtual());

        return ModelMapper.getMapper().map(this.inserir(origem), OrigemDTO.class);
    }
}
