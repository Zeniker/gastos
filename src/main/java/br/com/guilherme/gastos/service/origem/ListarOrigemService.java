package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ListarOrigemService {

    private final OrigemRepository origemRepository;
    private final SessaoService sessaoService;

    private List<Origem> listar() {

        return origemRepository.findByUsuario(sessaoService.getUsuarioAtual());
    }

    public List<OrigemDTO> listarDTO(){

        return listar().stream().map(OrigemDTO::new).collect(Collectors.toList());
    }
}
