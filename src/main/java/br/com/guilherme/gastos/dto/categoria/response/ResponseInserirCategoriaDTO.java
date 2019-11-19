package br.com.guilherme.gastos.dto.categoria.response;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirCategoriaDTO extends ResponseDTO {

    private CategoriaDTO categoria;

    public ResponseInserirCategoriaDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseInserirCategoriaDTO(Categoria categoria) {

        this.categoria = new CategoriaDTO(categoria);
    }
}
