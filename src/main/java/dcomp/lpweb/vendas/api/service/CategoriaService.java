package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private final GenericoService<Categoria> genericoService;
    
    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;

        genericoService = new GenericoService<>(categoriaRepository);
    }

    @Transactional(readOnly = true)
    public List<Categoria> todas() {
        return genericoService.todos();
    }

    @Transactional
    public Categoria salva(Categoria categoria) {
        return genericoService.salva(categoria );

    }

    @Transactional(readOnly = true)
    public Categoria buscaPor(Integer id ) {
        return genericoService.buscaPor(id );
    }

    @Transactional
    public void excluiPor(Integer id) {
        genericoService.excluirPor(id );
    }

    @Transactional
    public Categoria atualiza(Integer id, Categoria categoria) {
        return  genericoService.atualiza(categoria, id );
    }

    public List<Categoria> buscaCategorias(List<Integer> idsCategorias) {
        return categoriaRepository.findAllById(idsCategorias );
    }
}
