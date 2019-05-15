package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.controller.dto.ProdutoDTO;
import dcomp.lpweb.vendas.api.controller.response.Erro;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
import dcomp.lpweb.vendas.api.controller.validation.Validacao;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Value("${paginacao.qtd_por_pagina}")
    private Integer quantidadePorPagina;

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public Resposta<List<ProdutoDTO>> buscaTodos() {

        System.out.println("Paginação -> " + quantidadePorPagina );

        List<Produto> produtos = produtoService.todos();

        ArrayList<ProdutoDTO> produtosDTO = new ArrayList<>(produtos.size());

        produtos.forEach(p -> {
            ProdutoDTO produtoDTO = new ProdutoDTO().comDadosDe(p);
            produtosDTO.add(produtoDTO);
        });

        Resposta<List<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }


    @PostMapping
    public ResponseEntity<Resposta<ProdutoDTO>> salva(@Valid @RequestBody ProdutoDTO produtoDTO )  {

        Produto produtoSalvo = produtoService.salva(produtoDTO.getProduto() );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getId())
                .toUri();

        Resposta<ProdutoDTO> resposta = new Resposta<>();
        resposta.setDados(produtoDTO.comDadosDe(produtoSalvo ));

        return ResponseEntity.created(uri).body(resposta );
    }


    @GetMapping("/{id}")
    public Resposta<ProdutoDTO> buscaPor(@PathVariable Integer id) {

        Produto produto = produtoService.buscaPor(id);

        Resposta<ProdutoDTO> resposta = new Resposta<>();
        resposta.setDados(new ProdutoDTO().comDadosDe(produto ) );

        return resposta;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta<ProdutoDTO>> atualiza(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {

        Produto produto = produtoDTO.atualizaIgnorandoNulo(produtoService.buscaPor(id ) );

        Resposta<ProdutoDTO> resposta = new Resposta<>();

        Validacao<ProdutoDTO> validacao = new Validacao<>();
        List<Erro> erros =  validacao.valida(produtoDTO.comDadosDe(produto) );

        if (Objects.nonNull( erros ) &&  !erros.isEmpty() ) {
            resposta.setErros(erros );
            return ResponseEntity.badRequest().body(resposta );
        }

        Produto produtoAtualizado = produtoService.atualiza(id, produto);
        resposta.setDados(new ProdutoDTO().comDadosDe(produtoAtualizado) );

        return ResponseEntity.ok(resposta );
    }


    @PutMapping("/{id}/ativo")
    public ProdutoDTO atualiza(@PathVariable Integer id, @RequestBody Boolean ativo) {
        Produto produtoSalvo = produtoService.atualizaPropriedadeAtivo(id, ativo);
        return new ProdutoDTO().comDadosDe(produtoSalvo );
    }
}
