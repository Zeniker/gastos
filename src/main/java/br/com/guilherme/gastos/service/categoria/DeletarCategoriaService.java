package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DeletarCategoriaService {

    private final BuscarCategoriaService buscarCategoriaService;
    private final CategoriaRepository categoriaRepository;
    private final SessaoService sessaoService;

    private void deletar(Integer id) throws CategoriaNaoEncontradaException {

        Categoria categoria = buscarCategoriaService.buscar(id);

        categoriaRepository.delete(categoria);
    }

    public ResponseDTO deletarDTO(Integer id) throws CategoriaNaoEncontradaException {

        this.deletar(id);

        return new ResponseDTO();
    }
}
