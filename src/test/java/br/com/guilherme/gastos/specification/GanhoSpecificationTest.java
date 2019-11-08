package br.com.guilherme.gastos.specification;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GanhoSpecificationTest {

    @Test
    void byDataEntradaMes() {

        BooleanExpression expression = GanhoSpecification.byDataEntradaMes(LocalDate.now());
        assertNotNull(expression, "Expressão não deveria ser nula");
    }

    @Test
    void byDataEntradaAno() {
        BooleanExpression expression = GanhoSpecification.byDataEntradaAno(LocalDate.now());
        assertNotNull(expression, "Expressão não deveria ser nula");
    }
}