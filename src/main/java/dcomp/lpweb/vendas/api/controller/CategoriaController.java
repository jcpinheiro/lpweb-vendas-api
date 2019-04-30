package dcomp.lpweb.vendas.api.controller;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.service.CategoriaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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
    public ResponseEntity<Categoria> salva(@RequestBody Categoria categoria ) {

        Categoria categoriaSalva = categoriaService.salva(categoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getId())
                .toUri();

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{id}")
    public Categoria buscaPor(@PathVariable Integer id) {
        return categoriaService.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        categoriaService.excluiPor(id );
    }


    @PutMapping("/{id}")
    public Categoria altera(@PathVariable  Integer id, @RequestBody Categoria categoria) {
       return  categoriaService.atualiza(id, categoria );
    }

}
