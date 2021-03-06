package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseInserirMovimentacaoDTO extends ResponseDTO {

    private MovimentacaoDTO movimentacao;

    public ResponseInserirMovimentacaoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseInserirMovimentacaoDTO(MovimentacaoDTO movimentacao) {

        this.movimentacao = movimentacao;
    }
}
