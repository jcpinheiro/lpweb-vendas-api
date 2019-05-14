package dcomp.lpweb.vendas.api.repository;

import dcomp.lpweb.vendas.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByNome(String nome);

}
