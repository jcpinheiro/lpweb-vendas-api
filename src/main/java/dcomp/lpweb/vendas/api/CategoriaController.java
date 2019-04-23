package dcomp.lpweb.vendas.api;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping("/categorias")
    public List<Categoria> todas() {

        return categoriaService.todas();


    }
}
