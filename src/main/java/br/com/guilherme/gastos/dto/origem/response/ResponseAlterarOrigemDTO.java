package br.com.guilherme.gastos.dto.origem.response;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseAlterarOrigemDTO extends ResponseDTO {

    private OrigemDTO origem;

    public ResponseAlterarOrigemDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseAlterarOrigemDTO(OrigemDTO origem) {

        this.origem = origem;
    }
}
