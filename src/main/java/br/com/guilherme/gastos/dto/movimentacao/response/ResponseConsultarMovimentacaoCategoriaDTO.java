package br.com.guilherme.gastos.dto.movimentacao.response;

import br.com.guilherme.gastos.dto.ResponseDTO;
import br.com.guilherme.gastos.dto.movimentacao.MovimentacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseConsultarMovimentacaoCategoriaDTO extends ResponseDTO {

    private List<MovimentacaoDTO> movimentacoes;

    private BigDecimal valorTotal;

    public ResponseConsultarMovimentacaoCategoriaDTO(String mensagemErro) {

        super(mensagemErro);
    }

}
