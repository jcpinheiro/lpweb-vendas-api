package dcomp.lpweb.vendas.api.service;


import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.ProdutoRepository;
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

}




