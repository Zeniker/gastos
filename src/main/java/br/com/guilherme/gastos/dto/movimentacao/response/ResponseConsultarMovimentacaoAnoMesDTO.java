package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarMovimentacaoAnoMesDTO extends ResponseDTO {

    private List<MovimentacaoDTO> movimentacoes;

    public ResponseConsultarMovimentacaoAnoMesDTO(String mensagemErro) {

        super(mensagemErro);
    }

}
