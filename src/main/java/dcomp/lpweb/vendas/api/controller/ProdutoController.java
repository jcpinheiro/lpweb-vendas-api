package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public List<Produto> buscaTodos() {
        return produtoService.todos();
    }

    @PostMapping
    public Produto salva(@RequestBody Produto produto )  {
        return produtoService.salva(produto );
    }


    @PutMapping("/{id}")
    public Produto atualiza(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.atualiza(id, produto );
    }


    @PutMapping("/{id}/ativo")
    public Produto atualiza(@PathVariable Integer id, @RequestBody Boolean ativo) {
        return produtoService.atualizaPropriedadeAtivo(id, ativo );
    }



}
