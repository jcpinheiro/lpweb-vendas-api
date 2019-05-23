package dcomp.lpweb.vendas.api.service;


import dcomp.lpweb.vendas.api.model.ItemPedido;
import dcomp.lpweb.vendas.api.model.Pedido;
import dcomp.lpweb.vendas.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final GenericoService<Pedido> genericoService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository ) {
        this.pedidoRepository = pedidoRepository;
        this.genericoService = new GenericoService<>(pedidoRepository);
    }

    @Transactional
    public Pedido salva(Pedido pedido) {
         return genericoService.salva(pedido );
    }

    @Transactional(readOnly = true )
    public List<Pedido> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Pedido atualiza(Integer id, Pedido pedido) {
        return genericoService.atualiza(pedido, id);
    }

    @Transactional(readOnly = true )
    public Pedido buscaPor(Integer id) {
        return genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true )
    public Set<ItemPedido> itensDoPedido(Integer id) {
        Pedido pedido = buscaPor(id);
        return pedido.getItens();
    }
}
