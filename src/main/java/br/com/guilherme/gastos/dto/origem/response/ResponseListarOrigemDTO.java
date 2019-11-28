package br.com.guilherme.gastos.dto.origem.response;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.origem.OrigemDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseListarOrigemDTO extends ResponseDTO {

    private List<OrigemDTO> origens;

    public ResponseListarOrigemDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseListarOrigemDTO(List<OrigemDTO> origens) {

        this.origens = origens;

    }
}
