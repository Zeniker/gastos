package br.com.guilherme.gastos.dto.ganho.response;

import br.com.guilherme.gastos.domain.Ganho;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.ganho.GanhoDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirGanhoDTO extends ResponseDTO {

    private GanhoDTO ganho;

    public ResponseInserirGanhoDTO(Ganho ganho){
        this.ganho = new GanhoDTO(ganho);
    }

    public ResponseInserirGanhoDTO(String mensagemErro){
        super(mensagemErro);
    }

}
