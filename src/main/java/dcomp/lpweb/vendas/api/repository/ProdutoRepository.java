package dcomp.lpweb.vendas.api.repository;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.produto.ProdutoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery {


/*
    @Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias c WHERE p.nome LIKE %:nome% AND c IN (:categorias)")
    Page<Produto> busca(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable page);
*/

    Page<Produto> findDistinctByNomeContainingAndCategoriasIn (String nome, List<Categoria> categorias, Pageable page);

}
