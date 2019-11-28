package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.dto.categoria.request.RequestInserirCategoriaDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class InserirCategoriaService {

    private final CategoriaRepository categoriaRepository;

    private Categoria inserir(Categoria categoria) {
        categoria.setId(null);

        return categoriaRepository.save(categoria);
    }

    public CategoriaDTO inserirDTO(RequestInserirCategoriaDTO request){
        Categoria categoria = new Categoria();
        categoria.setDescricao(request.getDescricao());
        categoria.setTipoMovimentacao(request.getTipoMovimentacao());

        return new CategoriaDTO(this.inserir(categoria));
    }


}
