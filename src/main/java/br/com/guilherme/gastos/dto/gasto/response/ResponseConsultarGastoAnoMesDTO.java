package br.com.guilherme.gastos.dto.gasto.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.gasto.GastoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarGastoAnoMesDTO extends ResponseDTO {

    private List<GastoDTO> gastos;

    public ResponseConsultarGastoAnoMesDTO(String mensagemErro) {

        super(mensagemErro);
    }

}
