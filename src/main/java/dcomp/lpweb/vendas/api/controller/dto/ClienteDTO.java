package dcomp.lpweb.vendas.api.controller.dto;

import dcomp.lpweb.vendas.api.model.Endereco;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClienteDTO {


    private Integer id;

    @NotEmpty
    private String nome;

    @Email
    private String email;

    @NotEmpty
    @CPF
    private String cpf;

    private Set<@NotEmpty String> telefones = new LinkedHashSet<>();

    private Set<Endereco> enderecos = new LinkedHashSet<>();

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

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
