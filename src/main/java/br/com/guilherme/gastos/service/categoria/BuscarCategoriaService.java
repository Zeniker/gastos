package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarCategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final SessaoService sessaoService;

    public Categoria buscar(Integer id) throws CategoriaNaoEncontradaException {

        Optional<Categoria> optionalCategoria = categoriaRepository.findByIdAndUsuario(id,
                sessaoService.getUsuarioAtual());

        return optionalCategoria.orElseThrow(CategoriaNaoEncontradaException::new);
    }

    public CategoriaDTO buscarDTO(Integer id)
            throws CategoriaNaoEncontradaException {

        return new CategoriaDTO(this.buscar(id));
    }
}
