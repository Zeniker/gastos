package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Ganho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GanhoRepository extends JpaRepository<Ganho, Integer>, QuerydslPredicateExecutor {

}
