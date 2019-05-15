package dcomp.lpweb.vendas.api.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "preco_atual")
    private BigDecimal precoAtual;

    private Boolean ativo;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
               joinColumns = @JoinColumn(name = "produto_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id")    )
    private Set<Categoria> categorias = new LinkedHashSet<>();

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

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void adiciona(Categoria categoria ) {
        categorias.add(categoria );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", precoAtual=" + precoAtual +
                ", ativo=" + ativo +
                ", categorias=" + categorias +
                '}';
    }
}
