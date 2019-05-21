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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Resposta<List<ProdutoDTO>> buscaTodos() {

        List<Produto> produtos = produtoService.todos();

        List<ProdutoDTO> produtosDTO = produtos.stream()
                              .map(p -> new ProdutoDTO(p))
                              .collect(Collectors.toList());

        Resposta<List<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }


    @GetMapping("/paginacao")
    public Resposta<Page<ProdutoDTO>> buscaPaginada(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "3")Integer tamanho,
            @RequestParam(defaultValue = "nome")String orderBy,
            @RequestParam(defaultValue = "ASC")String direcao
    ) {

        final Pageable page = PageRequest.of(pagina, tamanho, Sort.Direction.valueOf(direcao), orderBy);

        Page<Produto> produtos = produtoService.buscaPaginada(page );
        Page<ProdutoDTO> produtosDTO = produtos
                                      .map(p -> new ProdutoDTO(p) );

        Resposta<Page<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }

    @GetMapping("/busca")
    public Resposta<Page<ProdutoDTO>> busca(
            @RequestParam(defaultValue = "") String nome,
            @RequestParam(defaultValue = "") String categorias,
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "3")Integer tamanho,
            @RequestParam(defaultValue = "nome")String orderBy,
            @RequestParam(defaultValue = "ASC")String direcao
    ) {

        final List<Integer> idsCategorias = Arrays.asList(categorias.split(",")).stream()
                .map(s -> Integer.valueOf(s))
                .collect(Collectors.toList());


        final Pageable page = PageRequest.of(pagina, tamanho, Sort.Direction.valueOf(direcao), orderBy);

        Page<Produto> produtos = produtoService.busca(nome, idsCategorias, page );

        Page<ProdutoDTO> produtosDTO = produtos
                .map(p -> new ProdutoDTO(p) );

        Resposta<Page<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }


    @GetMapping("/filtra")
    public Resposta<List<ProdutoDTO>> filtro(ProdutoFiltro produtoFiltro ) {

        List<Produto> produtos = produtoService.filtra(produtoFiltro );

        List<ProdutoDTO> produtosDTO = produtos.stream()
                .map(p -> new ProdutoDTO(p))
                .collect(Collectors.toList());

        Resposta<List<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }


    @GetMapping("/filtra/paginacao")
    public Resposta<Page<ProdutoDTO>> buscaPaginada(ProdutoFiltro filtro,
                  @RequestParam(defaultValue = "0") Integer pagina,
                  @RequestParam(defaultValue = "3")Integer tamanho,
                  @RequestParam(defaultValue = "nome") String orderBy,
                  @RequestParam(defaultValue = "ASC") String direcao ) {

        final Pageable page = PageRequest.of(pagina, tamanho, Sort.Direction.valueOf(direcao), orderBy);

        Page<Produto> produtos = produtoService.busca(filtro, page );

        Page<ProdutoDTO> produtosDTO = produtos.map( p -> new ProdutoDTO(p) );

        Resposta<Page<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO );

        return resposta;
    }

    @PostMapping
    public ResponseEntity<Resposta<ProdutoDTO>> salva(@Valid @RequestBody ProdutoDTO produtoDTO,
                                                      HttpServletResponse response )  {

        Produto produtoSalvo = produtoService.salva(produtoDTO.getProduto() );

        publisher.publishEvent(new HeaderLocationEvento(this, response, produtoSalvo.getId() ));

        Resposta<ProdutoDTO> resposta = new Resposta<>();
        resposta.setDados(produtoDTO.comDadosDe(produtoSalvo ));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(resposta );
    }


    @GetMapping("/{id}")
    public Resposta<ProdutoDTO> buscaPor(@PathVariable Integer id) {

        Produto produto = produtoService.buscaPor(id);

        Resposta<ProdutoDTO> resposta = new Resposta<>();
        resposta.setDados(new ProdutoDTO(produto) );

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
        resposta.setDados(new ProdutoDTO(produtoAtualizado ));

        return ResponseEntity.ok(resposta );
    }


    @PutMapping("/{id}/ativo")
    public ProdutoDTO atualiza(@PathVariable Integer id, @RequestBody Boolean ativo) {
        Produto produtoSalvo = produtoService.atualizaPropriedadeAtivo(id, ativo);
        return new ProdutoDTO(produtoSalvo );
    }
}
