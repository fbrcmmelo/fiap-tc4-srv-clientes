package br.com.fiap.fiaptc4srvclientes.gateway;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;

import java.util.Optional;

public interface ClienteGateway {
    Optional<Cliente> obterPorCpf(String cpf);
    Long criar(Cliente cliente);
    void atualizar(Cliente cliente);
    void excluir(Long id);
    Optional<Cliente> obterPorId(Long id);
}
