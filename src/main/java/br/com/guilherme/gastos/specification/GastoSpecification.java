package br.com.guilherme.gastos.specification;

import br.com.guilherme.gastos.domain.QGasto;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;

public class GastoSpecification {

    public static BooleanExpression byDataEntradaMes(LocalDate dataEntrada){
        QGasto ganho = QGasto.gasto;
        return ganho.dataEntrada.month().eq(dataEntrada.getMonthValue());
    }

    public static BooleanExpression byDataEntradaAno(LocalDate dataEntrada){
        QGasto ganho = QGasto.gasto;
        return ganho.dataEntrada.year().eq(dataEntrada.getYear());
    }

}
