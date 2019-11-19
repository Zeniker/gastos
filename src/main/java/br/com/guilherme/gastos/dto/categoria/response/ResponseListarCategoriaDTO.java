package br.com.guilherme.gastos.dto.categoria.response;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseListarCategoriaDTO extends ResponseDTO {

    private List<CategoriaDTO> categorias;

    public ResponseListarCategoriaDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseListarCategoriaDTO(List<Categoria> categorias) {
        this.categorias = new ArrayList<>();

        categorias.forEach(c -> this.categorias.add(new CategoriaDTO(c)));
    }
}