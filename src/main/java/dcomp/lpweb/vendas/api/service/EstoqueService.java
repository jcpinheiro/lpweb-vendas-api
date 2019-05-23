package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Pedido;
import dcomp.lpweb.vendas.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstoqueService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void baixarItensEstoque(Pedido pedido) {

        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedido.getId());

        if (optionalPedido.isPresent()) {
            pedido = optionalPedido.get();
            pedido.getItens().forEach(item -> item.baixaEstoque(item.getQuantidade()));
        }
    }

    public void retornarItensEstoque(Pedido pedido) {

        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedido.getId());

        if (optionalPedido.isPresent()) {
            pedido = optionalPedido.get();
            pedido.getItens().forEach(item -> item.getProduto()
                                                  .adicionaEstoque(item.getQuantidade()) );
        }
    }
}
