package dcomp.lpweb.vendas.api.controller;

import dcomp.lpweb.vendas.api.controller.dto.CategoriaDTO;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
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
    public Resposta<List<CategoriaDTO>> todas() {

        List<Categoria> categorias = categoriaService.todas();

        List<CategoriaDTO> categoriasDTO = new ArrayList<>(categorias.size() );

        categorias.forEach(categoria -> {
                                  CategoriaDTO categoriaDTO = new CategoriaDTO();
                                  BeanUtils.copyProperties(categoria, categoriaDTO);
                                  categoriasDTO.add(categoriaDTO );
                           });

        Resposta<List<CategoriaDTO>> resposta = new Resposta<>();
        resposta.setDados(categoriasDTO);

        return resposta;
    }


    @PostMapping
    public ResponseEntity<Resposta<CategoriaDTO>> salva(@RequestBody CategoriaDTO categoriaDTO ) {

        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDTO, categoria);

        Categoria categoriaSalva = categoriaService.salva(categoria );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getId())
                .toUri();

        BeanUtils.copyProperties(categoriaSalva, categoriaDTO );

        //TODO Refatorar este código, duplicado em outros métodos
        Resposta<CategoriaDTO> resposta = new Resposta<>();
        resposta.setDados(categoriaDTO );

        return ResponseEntity.created(uri).body(resposta );
    }

    @GetMapping("/{id}")
    public Resposta<CategoriaDTO> buscaPor(@PathVariable Integer id) {

        Categoria categoria = categoriaService.buscaPor(id);
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        BeanUtils.copyProperties(categoria, categoriaDTO);

        Resposta<CategoriaDTO> resposta = new Resposta<>();
        resposta.setDados(categoriaDTO );

        return resposta;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        categoriaService.excluiPor(id );
    }


    @PutMapping("/{id}")
    public Resposta<CategoriaDTO> altera(@PathVariable  Integer id, @RequestBody CategoriaDTO categoriaDTO) {


        Categoria categoria = categoriaService.buscaPor(id );

        BeanUtils.copyProperties(categoriaDTO,
                categoria,
                PropriedadesUtil.obterPropriedadesComNullDe(categoriaDTO) );

        Categoria categoriaAtualizada = categoriaService.atualiza(id, categoria);
        BeanUtils.copyProperties(categoriaAtualizada, categoriaDTO );

        Resposta<CategoriaDTO> resposta = new Resposta<>();
        resposta.setDados(categoriaDTO );

        return resposta;
    }

}
