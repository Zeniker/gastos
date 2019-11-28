package br.com.guilherme.gastos.service;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.categoria.BuscarCategoriaService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    private BuscarCategoriaService buscarCategoriaService;

    public CategoriaService(CategoriaRepository categoriaRepository, BuscarCategoriaService buscarCategoriaService) {

        this.categoriaRepository = categoriaRepository;
        this.buscarCategoriaService = buscarCategoriaService;
    }

    public Categoria alterar(Integer id, RequestAlterarCategoriaDTO request) {

        Categoria categoria = buscarCategoriaService.buscar(id);
        categoria.setDescricao(request.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {

        Categoria categoria = buscarCategoriaService.buscar(id);

        categoriaRepository.delete(categoria);
    }
}
