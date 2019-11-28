package br.com.guilherme.gastos.dto.origem.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirOrigemDTO extends ResponseDTO {

    private OrigemDTO origem;

    public ResponseInserirOrigemDTO(OrigemDTO origem){
        this.origem = origem;
    }

    public ResponseInserirOrigemDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
