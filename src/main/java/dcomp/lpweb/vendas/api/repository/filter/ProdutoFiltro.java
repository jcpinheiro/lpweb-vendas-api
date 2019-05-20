package dcomp.lpweb.vendas.api.repository.filter;

import java.math.BigDecimal;

public class ProdutoFiltro {

    private String nome;
    private BigDecimal precoDe;
    private BigDecimal precoAte;
    private Boolean ativo;
    private Integer categoriaId;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(BigDecimal precoDe) {
        this.precoDe = precoDe;
    }

    public BigDecimal getPrecoAte() {
        return precoAte;
    }

    public void setPrecoAte(BigDecimal precoAte) {
        this.precoAte = precoAte;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
}
