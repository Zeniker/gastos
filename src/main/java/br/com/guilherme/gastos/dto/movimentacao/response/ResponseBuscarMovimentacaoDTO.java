package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseBuscarMovimentacaoDTO extends ResponseDTO {

    private MovimentacaoDTO movimentacao;

    public ResponseBuscarMovimentacaoDTO(String mensagemErro) {

        super(mensagemErro);
    }

    public ResponseBuscarMovimentacaoDTO(Movimentacao movimentacao) {

        this.movimentacao = new MovimentacaoDTO(movimentacao);
    }
}
