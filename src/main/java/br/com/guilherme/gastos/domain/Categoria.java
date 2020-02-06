package br.com.guilherme.gastos.domain;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Categoria {

    @GenericGenerator(name="categoria_gen" , strategy="increment")
    @GeneratedValue(generator="categoria_gen")
    @Id
    private Integer id;

    @NotNull
    private String descricao;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

    @ManyToOne
    private Usuario usuario;

}
