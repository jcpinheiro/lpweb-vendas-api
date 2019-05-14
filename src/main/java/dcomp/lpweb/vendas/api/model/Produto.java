package dcomp.lpweb.vendas.api.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
@NamedEntityGraph(name = "Produto.categorias",
        attributeNodes = @NamedAttributeNode("categorias"))
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "preco_atual")
    private BigDecimal precoAtual;

    private Boolean ativo;


    // TODO Mudar para Set<Categoria>
    @ManyToMany
    @JoinTable(name = "produto_categoria",
               joinColumns = @JoinColumn(name = "produto_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id")    )
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

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void adiciona(Categoria categoria ) {
        categorias.add(categoria );
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
