package br.com.guilherme.gastos.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Ganho {

    @GenericGenerator(name="ganho_gen" , strategy="increment")
    @GeneratedValue(generator="ganho_gen")
    @Id
    private Integer id;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

    private String descricao;

}
