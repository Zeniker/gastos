package br.com.guilherme.gastos.dto.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    @Mapping("id")
    private Integer id;

    @Mapping("descricao")
    private String descricao;

    @Mapping("tipoMovimentacao")
    private TipoMovimentacao tipoMovimentacao;

    public CategoriaDTO(Categoria categoria) {

        this(categoria.getId(), categoria.getDescricao(), categoria.getTipoMovimentacao());
    }
}
