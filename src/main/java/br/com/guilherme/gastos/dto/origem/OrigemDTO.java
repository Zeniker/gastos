package br.com.guilherme.gastos.dto.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrigemDTO {

    private Integer id;
    private String nome;
    private TipoMovimentacao tipoMovimentacao;

    public OrigemDTO(Origem origem) {

        this(origem.getId(), origem.getNome(), origem.getTipoMovimentacao());
    }
}
