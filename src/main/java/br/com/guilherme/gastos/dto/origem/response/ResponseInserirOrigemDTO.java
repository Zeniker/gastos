package br.com.guilherme.gastos.dto.origem.response;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirOrigemDTO extends ResponseDTO {

    private OrigemDTO origem;

    public ResponseInserirOrigemDTO(Origem origem){
        this.origem = new OrigemDTO(origem);
    }

    public ResponseInserirOrigemDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
