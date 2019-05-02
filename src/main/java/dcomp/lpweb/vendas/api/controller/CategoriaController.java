package dcomp.lpweb.vendas.api.controller;

import dcomp.lpweb.vendas.api.controller.dto.CategoriaDTO;
import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.service.CategoriaService;

import dcomp.lpweb.vendas.api.util.PropriedadesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
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
    public List<CategoriaDTO> todas() {

        List<Categoria> categorias = categoriaService.todas();

        List<CategoriaDTO> categoriasDTO = new ArrayList<>(categorias.size() );

        categorias.forEach(categoria -> {
                                  CategoriaDTO categoriaDTO = new CategoriaDTO();
                                  BeanUtils.copyProperties(categoria, categoriaDTO);
                                  categoriasDTO.add(categoriaDTO );
                           });

        return categoriasDTO;
    }


    @PostMapping
    public ResponseEntity<CategoriaDTO> salva(@RequestBody CategoriaDTO categoriaDTO ) {

        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDTO, categoria);

        Categoria categoriaSalva = categoriaService.salva(categoria );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getId())
                .toUri();

        BeanUtils.copyProperties(categoriaSalva, categoriaDTO );

        return ResponseEntity.created(uri).body(categoriaDTO);
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscaPor(@PathVariable Integer id) {

        Categoria categoria = categoriaService.buscaPor(id);
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        BeanUtils.copyProperties(categoria, categoriaDTO);

        return categoriaDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        categoriaService.excluiPor(id );
    }


    @PutMapping("/{id}")
    public CategoriaDTO altera(@PathVariable  Integer id, @RequestBody CategoriaDTO categoriaDTO) {


        Categoria categoria = categoriaService.buscaPor(id );

        BeanUtils.copyProperties(categoriaDTO,
                categoria,
                PropriedadesUtil.obterPropriedadesComNullDe(categoriaDTO) );

        Categoria categoriaAtualizada = categoriaService.atualiza(id, categoria);
        BeanUtils.copyProperties(categoriaAtualizada, categoriaDTO );

        return categoriaDTO;
    }

}
