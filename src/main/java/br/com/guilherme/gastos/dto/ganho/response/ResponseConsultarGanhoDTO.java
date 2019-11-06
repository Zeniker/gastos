package br.com.guilherme.gastos.dto.ganho.response;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.ganho.GanhoDTO;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarGanhoDTO extends ResponseDTO {

    private GanhoDTO ganho;

    public ResponseConsultarGanhoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseConsultarGanhoDTO(Ganho ganho) {

        this.ganho = new GanhoDTO(ganho);
    }
}
