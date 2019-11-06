package br.com.guilherme.gastos.dto.gasto.response;

import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.gasto.GastoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirGastoDTO extends ResponseDTO {

    private GastoDTO gasto;

    public ResponseInserirGastoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseInserirGastoDTO(Gasto gasto) {

        this.gasto = new GastoDTO(gasto);
    }
}
