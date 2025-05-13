package br.com.fiap.fiaptc4srvclientes.usecase;


import java.util.Optional;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.exception.ClienteExistenteException;
import br.com.fiap.fiaptc4srvclientes.exception.ClienteSemEnderecoException;
import br.com.fiap.fiaptc4srvclientes.gateway.ClienteGateway;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public Long criar(Cliente cliente) {
        Optional<Cliente> clienteOp = clienteGateway.obterPorCpf(cliente.getCpf());
        if(clienteOp.isPresent()) {
            log.warn("Cliente já existe com CPF informado: {}", cliente.getCpf());
            throw new ClienteExistenteException();
        }

        if(!cliente.possuiEndereco()) {
            log.warn("Cliente sem endereço cadastrado");
            throw new ClienteSemEnderecoException();
        }

        return clienteGateway.criar(cliente);
    }
}