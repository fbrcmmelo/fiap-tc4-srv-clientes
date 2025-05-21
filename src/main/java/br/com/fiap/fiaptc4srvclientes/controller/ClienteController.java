package br.com.fiap.fiaptc4srvclientes.controller;

import br.com.fiap.fiaptc4srvclientes.model.Cliente;
import br.com.fiap.fiaptc4srvclientes.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // byId
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.listarCliente().stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping
    public List<Cliente> listClientes() {
        return clienteService.listarCliente();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @PutMapping
    public Cliente updateCliente(@RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(cliente);
    }
}

