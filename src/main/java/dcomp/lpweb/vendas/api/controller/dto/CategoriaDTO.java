package dcomp.lpweb.vendas.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dcomp.lpweb.vendas.api.model.Categoria;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nome;

    @Size(min = 5)
    private String descricao;

    private DTO<Categoria, CategoriaDTO> dto = new DTO<>(this);

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
        return dto.getEntity(new Categoria() );
    }

    public CategoriaDTO comDadosDe(Categoria categoria) {
        return dto.comDadosDe(categoria );
    }

    public Categoria atualizaIgnorandoNuloA(Categoria categoria) {
        return dto.mergeIgnorandoNulo(categoria );
    }


    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
