package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestAlterarCategoriaDTO;
import br.com.guilherme.gastos.exception.CategoriaNaoEncontradaException;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AlterarCategoriaService {

    private final BuscarCategoriaService buscarCategoriaService;
    private final CategoriaRepository categoriaRepository;

    private Categoria alterar(Categoria categoria) {
        if(categoria.getId() == null){
            throw new IllegalArgumentException("Id não pode ser nulo ao alterar categoria");
        }

        return categoriaRepository.save(categoria);
    }

    public CategoriaDTO alterarDTO(Integer id, RequestAlterarCategoriaDTO request) throws CategoriaNaoEncontradaException {

        Categoria categoria = buscarCategoriaService.buscar(id);
        ModelMapper.getMapper().map(request, categoria);

        return ModelMapper.getMapper().map(this.alterar(categoria), CategoriaDTO.class);
    }
}
