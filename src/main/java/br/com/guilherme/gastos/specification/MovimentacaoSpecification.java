package br.com.guilherme.gastos.specification;

import br.com.guilherme.gastos.domain.QMovimentacao;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;

public class MovimentacaoSpecification {

    public static BooleanExpression byDataEntradaMes(LocalDate dataEntrada){
        QMovimentacao movimentacao = QMovimentacao.movimentacao;
        return movimentacao.dataEntrada.month().eq(dataEntrada.getMonthValue());
    }

    public static BooleanExpression byDataEntradaAno(LocalDate dataEntrada){
        QMovimentacao movimentacao = QMovimentacao.movimentacao;
        return movimentacao.dataEntrada.year().eq(dataEntrada.getYear());
    }

    public static BooleanExpression byTipoMovimentacao(TipoMovimentacao tipoMovimentacao){

        if(tipoMovimentacao == null) return null;

        QMovimentacao movimentacao = QMovimentacao.movimentacao;
        return movimentacao.tipoMovimentacao.eq(tipoMovimentacao);
    }

}
