package dcomp.lpweb.vendas.api.service;


import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @Transactional(readOnly = true)
    public List<Produto> todos() {
        return produtoRepository.findAll();
    }

    private Produto buscaPorId(Integer id) {
        return produtoRepository.findById(id).get();

    }


    @Transactional
    public Produto atualizaPropriedadeAtivo(Integer id, Boolean ativo) {
        Produto produto = this.buscaPorId(id);
        produto.setAtivo(ativo );

        return produto;

    }

    @Transactional
    public Produto atualiza(Integer id, Produto produto) {

        Produto produtoSalvo = this.buscaPorId(id);
        BeanUtils.copyProperties(produto, produtoSalvo, "id");

        return produtoSalvo;

    }

    @Transactional
    public Produto salva(Produto produto) {
        return produtoRepository.save(produto );
    }
}




