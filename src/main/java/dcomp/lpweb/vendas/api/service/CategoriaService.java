package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> todas() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria salva(Categoria categoria) {
        return categoriaRepository.save(categoria );

    }

    @Transactional(readOnly = true)
    public Categoria buscaPor(Integer id) {
        return categoriaRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        categoriaRepository.deleteById(id );
    }

    @Transactional
    public Categoria atualiza(Integer id, Categoria categoria) {

        Categoria categoriaSalva = this.buscaPor(id);
        BeanUtils.copyProperties(categoria, categoriaSalva, "id");

        return  categoriaSalva;
    }
}
