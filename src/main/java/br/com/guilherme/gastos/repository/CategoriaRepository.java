package br.com.guilherme.gastos.repository;

import br.com.guilherme.gastos.domain.Categoria;
import br.com.guilherme.gastos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByUsuario(Usuario usuario);

    Optional<Categoria> findByIdAndUsuario(Integer id, Usuario usuario);

}
