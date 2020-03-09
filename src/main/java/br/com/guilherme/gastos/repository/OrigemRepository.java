package br.com.guilherme.gastos.repository;

import java.util.List;
import java.util.Optional;

import br.com.guilherme.gastos.domain.Origem;
import br.com.guilherme.gastos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Integer> {

    List<Origem> findByUsuario(Usuario usuario);
    Optional<Origem> findByIdAndUsuario(Integer id, Usuario usuario);

}
