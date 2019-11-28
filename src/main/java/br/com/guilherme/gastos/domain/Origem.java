package br.com.guilherme.gastos.domain;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Origem {

    @GenericGenerator(name="origem_gen" , strategy="increment")
    @GeneratedValue(generator="origem_gen")
    @Id
    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

}
