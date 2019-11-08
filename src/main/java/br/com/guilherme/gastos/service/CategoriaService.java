package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }

    public Categoria inserir(RequestInserirCategoriaDTO request) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(request.getDescricao());
        categoria.setTipoMovimentacao(request.getTipoMovimentacao());

        return categoriaRepository.save(categoria);
    }

    public Categoria buscar(Integer id) {

        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(CategoriaNaoEncontradaException::new);
    }
}
