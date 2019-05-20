package dcomp.lpweb.vendas.api.repository.produto;

import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.filter.ProdutoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoRepositoryQuery {

    List<Produto> filtrar(ProdutoFiltro filtro);

    Page<Produto> filtrar(ProdutoFiltro filtro, Pageable pageable);
}
