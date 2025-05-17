package br.com.fiap.fiaptc4srvclientes.usecase;


import java.util.Optional;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.exception.ClienteNaoEncontradoException;
import br.com.fiap.fiaptc4srvclientes.gateway.ClienteGateway;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcluirClienteUsecase {

    private final ClienteGateway clienteGateway;

    public void excluir(Long id) {
        Optional<Cliente> clienteOp = clienteGateway.obterPorId(id);
        if(clienteOp.isEmpty()) {
            log.warn("Cliente não encontrado para exclusão: id={}", id);
            throw new ClienteNaoEncontradoException();
        }

        clienteGateway.excluir(id);
    }
}