package dcomp.lpweb.vendas.api.service;


import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.ProdutoRepository;
import dcomp.lpweb.vendas.api.repository.filter.ProdutoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    private final GenericoService<Produto> genericoService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository ) {
        this.produtoRepository = produtoRepository;
        this.genericoService = new GenericoService<>(produtoRepository );
    }

    @Transactional(readOnly = true)
    public List<Produto> todos() {
        // ...
        return genericoService.todos();
    }

    @Transactional(readOnly = true)
    Produto buscaPorId(Integer id) {
        return genericoService.buscaPor(id );
    }

    @Transactional
    public Produto salva(Produto produto) {
        // TODO Melhorar o código de validaCategorias()
        validaCategorias( produto.getCategorias() );
        return genericoService.salva(produto);
    }


    @Transactional
    public Produto atualizaPropriedadeAtivo(Integer id, Boolean ativo) {
        Produto produto = this.buscaPorId(id);
        produto.setAtivo(ativo );

        return produto;
    }

    @Transactional
    public Produto atualiza(Integer id, Produto produto) {
        validaCategorias( produto.getCategorias() );
        return genericoService.atualiza(produto, id );
    }

    @Transactional(readOnly = true)
    public Produto buscaPor(Integer id) {
        return genericoService.buscaPor(id );
    }

    private void validaCategorias(Set<Categoria> categorias) {

        if (categorias !=null && !categorias.isEmpty() )
            categorias.forEach(this::accept);
    }

    // TODO Tratar as mensagens de erro e retornar juntamente com o produto salvo
    private void accept(Categoria c) {
        Objects.requireNonNull(c, "A categoria não pode ser nula");
        Integer id = Objects.requireNonNull(c.getId(), "O id da categoria não pode ser nulo");
        c = categoriaService.buscaPor(id );
    }

    public Page<Produto> buscaPaginada(Pageable page) {
       return produtoRepository.findAll(page );
    }

    public Page<Produto> busca(String nome, List<Integer> idsCategorias, Pageable page) {

        //TODO Adicinonar este método no Serviço Genérico
        List<Categoria> categorias = categoriaService.buscaCategorias(idsCategorias);

//        return produtoRepository.busca(nome, categorias, page );
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, page );
    }

    public List<Produto> filtra(ProdutoFiltro filtro) {
        return produtoRepository.filtrar(filtro );
    }

    public Page<Produto> busca(ProdutoFiltro filtro, Pageable pageable) {
        return produtoRepository.filtrar(filtro, pageable );
    }
}




