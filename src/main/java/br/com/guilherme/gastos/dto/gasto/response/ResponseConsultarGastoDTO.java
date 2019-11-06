package br.com.guilherme.gastos.dto.gasto.response;

import br.com.guilherme.gastos.domain.Gasto;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.gasto.GastoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarGastoDTO extends ResponseDTO {

    private GastoDTO gastoDTO;

    public ResponseConsultarGastoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseConsultarGastoDTO(Gasto gasto) {

        this.gastoDTO = new GastoDTO(gasto);
    }
}
