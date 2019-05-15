package dcomp.lpweb.vendas.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDTO {

    private Integer id;

    @NotEmpty
    private String nome;

    @Positive
    private BigDecimal precoAtual;

    private Boolean ativo;

    @JsonProperty("categorias")
    private Set<CategoriaDTO> categoriasDTO = new LinkedHashSet<>();

    @Autowired
    private CategoriaService categoriaService;

    private DTO<Produto, ProdutoDTO> dto = new DTO<>(this);

    public ProdutoDTO() {  }

    public ProdutoDTO(Produto produto ) {
        this.comDadosDe(produto );
    }

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

    public Set<CategoriaDTO> getCategoriasDTO() {
        return categoriasDTO;
    }

    public void setCategoriasDTO(Set<CategoriaDTO> categoriasDTO) {
        this.categoriasDTO = categoriasDTO;
    }

    @JsonIgnore
    public Produto getProduto() {
        Produto p = dto.getEntity(new Produto() );
        categoriasDTO.forEach(cDTO -> p.adiciona(cDTO.getCategoria()) );

        return p;
    }

    public ProdutoDTO comDadosDe(Produto produto) {
        dto.comDadosDe(produto );


        if (existeCategoriasEm(produto)) {
            adicionaAsCategoriasDe(produto );
        }
        return this;
    }


    public Produto atualizaIgnorandoNulo(Produto produto) {

        produto = dto.mergeIgnorandoNulo(produto);

        Set<Categoria> categorias = this.getCategoriasDTO()
                .stream()
                .map(dto -> dto.getCategoria())
                .collect(Collectors.toSet());

        produto.setCategorias(categorias );

        return produto;

    }

    private void adicionaAsCategoriasDe(final Produto produto) {

        categoriasDTO = produto.getCategorias()
                               .stream()
                               .map(categoria -> new CategoriaDTO(categoria))
                               .collect(Collectors.toSet());
    }

    private boolean existeCategoriasEm(Produto produto) {
        return Objects.nonNull(produto.getCategorias() )
           && Objects.nonNull(this.categoriasDTO )
           && this.categoriasDTO.isEmpty();
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", precoAtual=" + precoAtual +
                ", ativo=" + ativo +
                ", categoriasDTO=" + categoriasDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}