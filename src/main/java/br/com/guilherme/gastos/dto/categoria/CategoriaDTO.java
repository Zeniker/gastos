package br.com.guilherme.gastos.dto.categoria;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDTO {

    private Integer id;
    private String descricao;
    private TipoMovimentacao tipoMovimentacao;

    public CategoriaDTO(Categoria categoria) {

        this(categoria.getId(), categoria.getDescricao(), categoria.getTipoMovimentacao());
    }
}
