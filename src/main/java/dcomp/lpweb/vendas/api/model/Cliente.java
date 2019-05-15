package dcomp.lpweb.vendas.api.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "cliente")
public class Cliente {

     @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nome;

    @Email
    private String email;

    @NotEmpty
    @CPF
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;


    @ElementCollection
    @CollectionTable(
            name = "telefones",
            joinColumns = @JoinColumn(name = "cliente_id")
    )
    @Column(name = "numero")
    private Set<@NotEmpty String> telefones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<@NotEmpty Endereco> enderecos = new LinkedHashSet<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void adicionaUm(Endereco endereco) {
        this.enderecos.add(endereco );
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}