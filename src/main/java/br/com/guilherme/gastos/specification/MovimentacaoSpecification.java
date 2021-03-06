package br.com.guilherme.gastos.specification;

import java.time.LocalDate;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.QMovimentacao;
import br.com.guilherme.gastos.domain.Usuario;
import br.com.guilherme.gastos.enums.TipoMovimentacao;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

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

    public static BooleanExpression byCategoria(Categoria categoria){

        return QMovimentacao.movimentacao.categoria.eq(categoria);
    }

    public static BooleanExpression byUsuario(Usuario usuario){

        return QMovimentacao.movimentacao.usuario.eq(usuario);
    }

    public static OrderSpecifier<LocalDate> orderByDataEntrada(){
        return QMovimentacao.movimentacao.dataEntrada.asc();
    }

}
