package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>, QuerydslPredicateExecutor {

}
