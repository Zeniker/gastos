package br.com.guilherme.gastos.service.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.repository.OrigemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DeletarOrigemService {

    private BuscarOrigemService buscarOrigemService;

    private OrigemRepository origemRepository;

    private void deletar(Integer id) {

        Origem origem = buscarOrigemService.buscar(id);

        origemRepository.delete(origem);

    }

    public ResponseDTO deletarDTO(Integer id) {

        this.deletar(id);

        return new ResponseDTO();
    }
}
