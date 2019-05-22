package dcomp.lpweb.vendas.api.repository.produto;

import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.filter.ProdutoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProdutoRepositoryQuery {

   Page<Produto> filtrar(ProdutoFiltro filtro, Pageable pageable);
}
