package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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

/*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salva(@RequestBody Produto produto, HttpServletResponse response )  {

        Produto produtoSalvo = produtoService.salva(produto);

        String uriString = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getId())
                .toUriString();


        response.setHeader("Location", uriString);

        return produtoSalvo;
    }
*/



    @PostMapping
    public ResponseEntity<Produto> salva(@RequestBody Produto produto )  {

        Produto produtoSalvo = produtoService.salva(produto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produtoSalvo);
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
