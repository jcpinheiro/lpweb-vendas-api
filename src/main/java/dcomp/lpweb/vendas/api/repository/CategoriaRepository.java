package dcomp.lpweb.vendas.api.repository;

import dcomp.lpweb.vendas.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
