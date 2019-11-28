package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DeletarCategoriaService {

    private final BuscarCategoriaService buscarCategoriaService;
    private final CategoriaRepository categoriaRepository;

    private void deletar(Integer id) {

        Categoria categoria = buscarCategoriaService.buscar(id);

        categoriaRepository.delete(categoria);
    }

    public ResponseDTO deletarDTO(Integer id) {

        this.deletar(id);

        return new ResponseDTO();
    }
}
