package br.com.fiap.fiaptc4srvclientes.usecase;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.exception.ClienteNaoEncontradoException;
import br.com.fiap.fiaptc4srvclientes.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObterClienteUsecase {

    private final ClienteGateway clienteGateway;

    public Cliente obterPorId(Long id) {
        Optional<Cliente> clienteOp = clienteGateway.obterPorId(id);
        if(clienteOp.isEmpty()) {
            log.warn("Cliente não encontrado: id={}", id);
            throw new ClienteNaoEncontradoException();
        }
        return clienteOp.get();
    }

    public Cliente obterPorCpf(String cpf) {
        Optional<Cliente> clienteOp = clienteGateway.obterPorCpf(cpf);
        if(clienteOp.isEmpty()) {
            log.warn("Cliente não encontrado: cpf={}", cpf);
            throw new ClienteNaoEncontradoException();
        }
        return clienteOp.get();
    }
}