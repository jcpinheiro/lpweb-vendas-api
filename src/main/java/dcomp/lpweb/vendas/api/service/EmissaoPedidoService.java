package dcomp.lpweb.vendas.api.service;

import dcomp.lpweb.vendas.api.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissaoPedidoService {

    @Autowired
    private final PedidoService pedidoService;

    public EmissaoPedidoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public Pedido emitir(Pedido pedido) {
        return pedido;
    }


}
