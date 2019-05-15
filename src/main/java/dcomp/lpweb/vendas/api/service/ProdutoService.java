package dcomp.lpweb.vendas.api.service;


import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("11111111111 " + produto );
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
}




