package br.com.guilherme.gastos.specification;

import br.com.guilherme.gastos.domain.QGanho;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;

public class GanhoSpecification {

    public static BooleanExpression byDataEntradaMes(LocalDate dataEntrada){
        QGanho ganho = QGanho.ganho;
        return ganho.dataEntrada.month().eq(dataEntrada.getMonthValue());
    }

    public static BooleanExpression byDataEntradaAno(LocalDate dataEntrada){
        QGanho ganho = QGanho.ganho;
        return ganho.dataEntrada.year().eq(dataEntrada.getYear());
    }

}
