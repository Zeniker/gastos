package br.com.guilherme.gastos.service.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import br.com.guilherme.gastos.repository.CategoriaRepository;
import br.com.guilherme.gastos.service.sessao.SessaoService;
import br.com.guilherme.gastos.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ListarCategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final SessaoService sessaoService;

    private List<Categoria> listar() {

        return categoriaRepository.findByUsuario(sessaoService.getUsuarioAtual());
    }

    public List<CategoriaDTO> listarDTO() {

        return listar().stream()
                .map(categoria -> ModelMapper.getMapper().map(categoria, CategoriaDTO.class))
                .collect(Collectors.toList());
    }
}
