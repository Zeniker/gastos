package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseBuscarMovimentacaoDTO extends ResponseDTO {

    private MovimentacaoDTO movimentacao;

    public ResponseBuscarMovimentacaoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseBuscarMovimentacaoDTO(MovimentacaoDTO movimentacao) {

        this.movimentacao = movimentacao;
    }
}
