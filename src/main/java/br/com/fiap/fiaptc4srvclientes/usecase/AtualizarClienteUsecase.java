package br.com.fiap.fiaptc4srvclientes.usecase;


import java.util.Optional;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.gateway.ClienteGateway;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtualizarClienteUsecase {

    private final ClienteGateway clienteGateway;

    public void atualizar(Cliente cliente) {
        Optional<Cliente> clienteExistenteOp = clienteGateway.obterPorId(cliente.getId());
        if(clienteExistenteOp.isEmpty()) {
            log.warn("Cliente não encontrado para atualização: id={}", cliente.getId());
            throw new ClienteNaoEncontradoException();
        }

        clienteGateway.atualizar(cliente);
    }
}