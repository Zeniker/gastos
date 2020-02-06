package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Movimentacao;
import br.com.guilherme.gastos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>, QuerydslPredicateExecutor {

    List<Movimentacao> findByUsuario(Usuario usuario);
    Optional<Movimentacao> findByIdAndUsuario(Integer id, Usuario usuario);

}
