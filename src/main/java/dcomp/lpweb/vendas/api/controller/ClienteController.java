package dcomp.lpweb.vendas.api.controller;


import dcomp.lpweb.vendas.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.vendas.api.model.Cliente;
import dcomp.lpweb.vendas.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ApplicationEventPublisher publisher;

   private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> todos() {
        return clienteService.todos();
    }


   @PostMapping
    public ResponseEntity<Cliente> cria(@Validated @RequestBody Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = clienteService.salva(cliente );

        publisher.publishEvent(new HeaderLocationEvento(this, response, clienteSalvo.getId()) );

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(clienteSalvo );
    }

    @GetMapping("/{id}")
    public Cliente buscaPor(@PathVariable Integer id) {
        return clienteService.buscaPor(id );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable Integer id, @Validated @RequestBody Cliente cliente ) {
        Cliente clienteManager = clienteService.atualiza(id, cliente );
        return ResponseEntity.ok(clienteManager );
    }

}
