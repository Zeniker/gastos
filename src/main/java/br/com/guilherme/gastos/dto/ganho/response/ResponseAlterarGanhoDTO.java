package br.com.guilherme.gastos.dto.ganho.response;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.ganho.GanhoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseAlterarGanhoDTO extends ResponseDTO {

    private GanhoDTO ganho;

    public ResponseAlterarGanhoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseAlterarGanhoDTO(Ganho ganho) {

        this.ganho = new GanhoDTO(ganho);
    }
}
