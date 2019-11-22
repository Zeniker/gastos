package br.com.guilherme.gastos.dto.origem;

import br.com.guilherme.gastos.domain.Origem;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrigemDTO {

    private Integer id;
    private String nome;

    public OrigemDTO(Origem origem) {

        this(origem.getId(), origem.getNome());
    }
}
