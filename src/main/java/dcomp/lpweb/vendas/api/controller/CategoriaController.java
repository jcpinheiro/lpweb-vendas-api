package dcomp.lpweb.vendas.api.controller;

import dcomp.lpweb.vendas.api.controller.dto.CategoriaDTO;
import dcomp.lpweb.vendas.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.vendas.api.controller.response.Erro;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
import dcomp.lpweb.vendas.api.controller.validation.Validacao;
import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public Resposta<List<CategoriaDTO>> todas() {
        List<CategoriaDTO> categoriasDTO = categoriaService.todas()
                                   .stream()
                                   .map(categoria -> new CategoriaDTO(categoria))
                                   .collect(Collectors.toList());

        return Resposta.comDadosDe(categoriasDTO);
    }


    @PostMapping
    public ResponseEntity<Resposta<CategoriaDTO>> salva(@Valid @RequestBody CategoriaDTO categoriaDTO,
                                                        HttpServletResponse response) {
        Categoria categoriaSalva = categoriaService.salva(categoriaDTO.getCategoria());

        publisher.publishEvent(new HeaderLocationEvento(this, response, categoriaSalva.getId()) );

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(Resposta.comDadosDe(new CategoriaDTO(categoriaSalva)));
    }

    @GetMapping("/{id}")
    public Resposta<CategoriaDTO> buscaPor(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscaPor(id);
        return Resposta.comDadosDe(new CategoriaDTO(categoria ));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        categoriaService.excluiPor(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Resposta<CategoriaDTO>> atualizar(@PathVariable Integer id,
                                                            @RequestBody CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaDTO.atualizaIgnorandoNuloA(categoriaService.buscaPor(id));

        List<Erro> erros = this.getErros(new CategoriaDTO(categoria) );
        if (existe(erros)) {
            return ResponseEntity.badRequest().body(Resposta.com(erros) );
        }

        Categoria categoriaAtualizada = categoriaService.atualiza(id, categoria);
        return ResponseEntity.ok(Resposta.comDadosDe(new CategoriaDTO(categoriaAtualizada )));
    }

    private boolean existe(List<Erro> erros) {
        return Objects.nonNull( erros ) &&  !erros.isEmpty();
    }

    private List<Erro> getErros(CategoriaDTO dto) {
        Validacao<CategoriaDTO> validacao = new Validacao<>();
        return validacao.valida(dto);
    }
}
