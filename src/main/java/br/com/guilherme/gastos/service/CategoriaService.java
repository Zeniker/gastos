package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscar(Integer id) {

        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(CategoriaNaoEncontradaException::new);
    }

    public Categoria alterar(Integer id, RequestAlterarCategoriaDTO request) {

        Categoria categoria = this.buscar(id);
        categoria.setDescricao(request.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {

        Categoria categoria = this.buscar(id);

        categoriaRepository.delete(categoria);
    }
}
