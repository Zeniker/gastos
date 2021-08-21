package br.com.guilherme.gastos.dto.origem;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrigemDTO {

    @Mapping("id")
    private Integer id;

    @Mapping("nome")
    private String nome;

    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;
}
