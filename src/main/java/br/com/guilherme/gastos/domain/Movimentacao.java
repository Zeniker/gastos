package br.com.guilherme.gastos.domain;

import br.com.guilherme.gastos.enums.TipoMovimentacao;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Movimentacao {

    @GenericGenerator(name="movimentacao_gen" , strategy="increment")
    @GeneratedValue(generator="movimentacao_gen")
    @Id
    private Integer id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataEntrada;

    @NotNull
    private String descricao;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Origem origem;

    @ManyToOne
    private Usuario usuario;

}
