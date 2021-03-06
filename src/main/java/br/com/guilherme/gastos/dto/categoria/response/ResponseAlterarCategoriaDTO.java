package br.com.guilherme.gastos.dto.categoria.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.categoria.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseAlterarCategoriaDTO extends ResponseDTO {

    private CategoriaDTO categoria;

    public ResponseAlterarCategoriaDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseAlterarCategoriaDTO(CategoriaDTO categoria) {

        this.categoria = categoria;
    }
}
