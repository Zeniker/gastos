package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer>, QuerydslPredicateExecutor {

}
