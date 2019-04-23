package dcomp.lpweb.vendas.api.controller;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping
    public List<Categoria> todas() {
        return categoriaService.todas();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria salva(@RequestBody Categoria categoria) {
        return categoriaService.salva(categoria );
    }

    @GetMapping("/{id}")
    public Categoria buscaPor(@PathVariable Integer id) {
        return categoriaService.buscaPor(id );

    }

}
