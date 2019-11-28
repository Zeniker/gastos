package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarCategoriaService {

    private CategoriaRepository categoriaRepository;

    public ListarCategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private List<Categoria> listar() {

        return categoriaRepository.findAll();
    }

    public List<CategoriaDTO> listarDTO() {
        List<Categoria> listaCategoria = listar();

        return listaCategoria.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }
}
