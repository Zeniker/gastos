package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Origem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Integer> {

}
