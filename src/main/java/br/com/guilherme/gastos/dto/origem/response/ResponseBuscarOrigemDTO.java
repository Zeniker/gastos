package br.com.guilherme.gastos.dto.origem.response;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseBuscarOrigemDTO extends ResponseDTO {

    private OrigemDTO origem;

    public ResponseBuscarOrigemDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseBuscarOrigemDTO(OrigemDTO origem) {
        this.origem = origem;
    }
}
