package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Cliente;
import dcomp.lpweb.vendas.api.model.Endereco;
import dcomp.lpweb.vendas.api.repository.ClienteRepository;
import dcomp.lpweb.vendas.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final GenericoService<Cliente> genericoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;

        this.genericoService = new GenericoService<Cliente>(clienteRepository );
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscaPor(String nome) {
        return Optional.ofNullable( clienteRepository.findByNome(nome ) );
    }


    @Transactional(readOnly = true)
    public Cliente buscaPor(Integer id) {
        // ...
        return this.genericoService.buscaPor(id );
    }


    @Transactional
    public Cliente salva(Cliente cliente ) {
        return this.genericoService.salva(cliente);
    }


    @Transactional(readOnly = true)
    public List<Cliente> todos() {
        return genericoService.todos();
    }


    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }


    @Transactional
    public Cliente atualiza(Integer id, Cliente cliente) {
        return this.genericoService.atualiza(cliente, id);
    }
}
