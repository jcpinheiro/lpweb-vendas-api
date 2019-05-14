package dcomp.lpweb.vendas.api.repository;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
