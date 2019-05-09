package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.controller.dto.ProdutoDTO;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
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
    public Resposta<List<ProdutoDTO>> buscaTodos() {

        List<Produto> produtos = produtoService.todos();
        ArrayList<ProdutoDTO> produtosDTO = new ArrayList<>(produtos.size());

        produtos.forEach(p -> produtosDTO.add(new ProdutoDTO().comDadosDe(p)));

        Resposta<List<ProdutoDTO>> resposta = new Resposta<>();
        resposta.setDados(produtosDTO);

        return resposta;
    }


    @PostMapping
    public ResponseEntity<Resposta<ProdutoDTO>> salva(@RequestBody ProdutoDTO produtoDTO )  {

        Produto produtoSalvo = produtoService.salva(produtoDTO.getProduto());

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
    public ProdutoDTO atualiza(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.atualiza(id, produtoDTO.getProduto());
        return produtoDTO.comDadosDe(produtoAtualizado );
    }


    @PutMapping("/{id}/ativo")
    public ProdutoDTO atualiza(@PathVariable Integer id, @RequestBody Boolean ativo) {
        Produto produtoSalvo = produtoService.atualizaPropriedadeAtivo(id, ativo);
        return new ProdutoDTO().comDadosDe(produtoSalvo );

    }



}
