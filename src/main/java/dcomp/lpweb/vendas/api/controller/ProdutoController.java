package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.controller.dto.ProdutoDTO;
import dcomp.lpweb.vendas.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.vendas.api.controller.response.Erro;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
import dcomp.lpweb.vendas.api.controller.validation.Validacao;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.filter.ProdutoFiltro;
import dcomp.lpweb.vendas.api.service.CategoriaService;
import dcomp.lpweb.vendas.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Value("${paginacao.qtd_por_pagina}")
    private Integer quantidadePorPagina;

    private final ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }



    @GetMapping
    public Resposta<Page<ProdutoDTO>> busca(ProdutoFiltro filtro, Pageable page  ) {

        Page<Produto> produtos = produtoService.busca(filtro, page );

        Page<ProdutoDTO> pageProdutosDTO = produtos.map( p -> new ProdutoDTO(p) );

        return Resposta.comDadosDe(pageProdutosDTO );
    }

    @PostMapping
    public ResponseEntity<Resposta<ProdutoDTO>> salva(@Valid @RequestBody ProdutoDTO produtoDTO,
                                                      HttpServletResponse response )  {

        Produto produtoSalvo = produtoService.salva(produtoDTO.getProduto() );

        publisher.publishEvent(new HeaderLocationEvento(this, response, produtoSalvo.getId() ));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Resposta.comDadosDe(new ProdutoDTO(produtoSalvo ) ) );
    }


    @GetMapping("/{id}")
    public Resposta<ProdutoDTO> buscaPor(@PathVariable Integer id) {
      Produto produto = produtoService.buscaPor(id);
      return Resposta.comDadosDe(new ProdutoDTO(produto) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta<ProdutoDTO>> atualiza(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {

        Produto produto = produtoDTO.atualizaIgnorandoNulo(produtoService.buscaPor(id ) );

        List<Erro> erros = this.getErros(new ProdutoDTO(produto) );
        if (existe(erros) ) {
            return ResponseEntity.badRequest().body(Resposta.com(erros ) );
        }

        Produto produtoAtualizado = produtoService.atualiza(id, produto);
        return ResponseEntity.ok(Resposta.comDadosDe(new ProdutoDTO(produtoAtualizado )) );
    }

    //TODO generalizar este código, está duplicado nos outros controllers
    private boolean existe(List<Erro> erros) {
        return Objects.nonNull( erros ) &&  !erros.isEmpty();
    }


    private List<Erro> getErros(ProdutoDTO dto) {
        Validacao<ProdutoDTO> validacao = new Validacao<>();
        return validacao.valida(dto);
    }


    @PutMapping("/{id}/ativo")
    public ProdutoDTO atualiza(@PathVariable Integer id, @RequestBody Boolean ativo) {
        Produto produtoSalvo = produtoService.atualizaPropriedadeAtivo(id, ativo);
        return new ProdutoDTO(produtoSalvo );
    }
}
