package dcomp.lpweb.vendas.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.util.PropriedadesUtil;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nome;

    @Size(min = 5, message = "A descrição deve ter o mínimo de 5 caracteres")
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @JsonIgnore
    public Categoria getCategoria() {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(this, categoria);

        return categoria;
    }

    public CategoriaDTO comDadosDe(Categoria categoria) {
        BeanUtils.copyProperties(categoria, this );

        return this;

    }

    public Categoria atualizaIgnorandoNuloA(Categoria categoria) {

        BeanUtils.copyProperties(this,
                categoria,
                PropriedadesUtil.obterPropriedadesComNullDe(this) );

        return categoria;
    }
}
