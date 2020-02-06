package br.com.guilherme.gastos.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Usuario {

    @GenericGenerator(name="usuario_gen" , strategy="increment")
    @GeneratedValue(generator="usuario_gen")
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    private String nome;

    @NotNull
    private String senha;
}
