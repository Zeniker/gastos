package br.com.guilherme.gastos.dto.gasto.response;

import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.gasto.GastoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseAlterarGastoDTO extends ResponseDTO {

    private GastoDTO gasto;

    public ResponseAlterarGastoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseAlterarGastoDTO(Gasto gasto) {

        this.gasto = new GastoDTO(gasto);
    }
}
