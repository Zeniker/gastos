package br.com.guilherme.gastos.specification;

import java.time.LocalDate;

import br.com.guilherme.gastos.TesteUnitario;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MovimentacaoSpecificationTest implements TesteUnitario {

    @Test
    void byDataEntradaMes() {

        BooleanExpression expression = MovimentacaoSpecification.byDataEntradaMes(LocalDate.now());
        assertNotNull(expression, "Expressão não deveria ser nula");
    }

    @Test
    void byDataEntradaAno() {
        BooleanExpression expression = MovimentacaoSpecification.byDataEntradaAno(LocalDate.now());
        assertNotNull(expression, "Expressão não deveria ser nula");
    }

    @Test
    void byTipoMovimentacao() {
        BooleanExpression expression = MovimentacaoSpecification.byTipoMovimentacao(TipoMovimentacao.GANHO);
        assertNotNull(expression, "Expressão não deveria ser nula");
    }
}