package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarCategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id) {

        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(CategoriaNaoEncontradaException::new);
    }

    public CategoriaDTO buscarDTO(Integer id) {

        return new CategoriaDTO(this.buscar(id));
    }
}
