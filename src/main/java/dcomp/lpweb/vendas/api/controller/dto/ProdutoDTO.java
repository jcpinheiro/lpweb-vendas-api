package dcomp.lpweb.vendas.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.util.PropriedadesUtil;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Integer id;

    @NotEmpty
    private String nome;

    @DecimalMin(value = "0.01")
    private BigDecimal precoAtual;

    @NotNull
    private Boolean ativo;

    private List<Categoria> categorias = new ArrayList<>();

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

    public BigDecimal getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(BigDecimal precoAtual) {
        this.precoAtual = precoAtual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias ) {
        this.categorias = categorias;
    }


    @JsonIgnore
    public Produto getProduto() {
        Produto produto = new Produto();
        BeanUtils.copyProperties(this, produto);

        return produto;
    }

    public ProdutoDTO comDadosDe(Produto produto) {
        BeanUtils.copyProperties(produto, this);
        return this;
    }

    public Produto atualizaIgnorandoNuloEm(Produto produto) {

        BeanUtils.copyProperties(this,
                produto,
                PropriedadesUtil.obterPropriedadesComNullDe(this));

        return produto;
    }


}