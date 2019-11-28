package br.com.guilherme.gastos.dto.categoria.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseListarCategoriaDTO extends ResponseDTO {

    private List<CategoriaDTO> categorias;

    public ResponseListarCategoriaDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseListarCategoriaDTO(List<CategoriaDTO> categorias) {

        this.categorias = categorias;
    }
}
