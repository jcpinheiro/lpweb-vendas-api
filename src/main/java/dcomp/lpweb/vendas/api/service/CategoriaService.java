package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> todas() {

        return categoriaRepository.findAll();
    }
}
