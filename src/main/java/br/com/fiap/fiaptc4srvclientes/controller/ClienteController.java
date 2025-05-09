package br.com.fiap.fiaptc4srvclientes.controller;

import br.com.fiap.fiaptc4srvclientes.model.Cliente;
import br.com.fiap.fiaptc4srvclientes.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    public List<Cliente> listClientes() {
            return clienteService.listarCliente();
    }


    public Cliente createCliente(Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    public Cliente updateCliente(Cliente cliente) {
        return clienteService.atualizarCliente(cliente);
    }
}
