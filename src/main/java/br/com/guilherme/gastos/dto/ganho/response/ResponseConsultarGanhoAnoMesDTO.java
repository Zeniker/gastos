package br.com.guilherme.gastos.dto.ganho.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.ganho.GanhoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarGanhoAnoMesDTO extends ResponseDTO {

    private List<GanhoDTO> ganhos;

    public ResponseConsultarGanhoAnoMesDTO(String mensagemErro) {

        super(mensagemErro);
    }
}
