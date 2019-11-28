package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseAlterarMovimentacaoDTO extends ResponseDTO {

    private MovimentacaoDTO movimentacao;

    public ResponseAlterarMovimentacaoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseAlterarMovimentacaoDTO(MovimentacaoDTO movimentacao) {

        this.movimentacao = movimentacao;
    }
}
