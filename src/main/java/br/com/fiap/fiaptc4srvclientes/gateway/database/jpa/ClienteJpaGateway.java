package br.com.fiap.fiaptc4srvclientes.gateway.database.jpa;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.domain.Endereco;
import br.com.fiap.fiaptc4srvclientes.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.fiaptc4srvclientes.gateway.ClienteGateway;
import br.com.fiap.fiaptc4srvclientes.gateway.database.entity.ClienteEntity;
import br.com.fiap.fiaptc4srvclientes.gateway.database.entity.EnderecoEntity;
import br.com.fiap.fiaptc4srvclientes.gateway.database.jpa.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteJpaGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> obterPorCpf(String cpf) {
        try {
            Optional<ClienteEntity> clienteEntityOp = clienteRepository.findByCpf(cpf);

            if (clienteEntityOp.isEmpty()) {
                log.info("Cliente não encontrado: cpf={}", cpf);
                return Optional.empty();
            }

            ClienteEntity clienteEntity = clienteEntityOp.get();
            Cliente cliente = mapToDomain(clienteEntity);

            return Optional.of(cliente);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErroAoAcessarRepositorioException("Falha ao executar o metodo" + e);
        }
    }

    @Override
    public Long criar(Cliente cliente) {
        try {
            ClienteEntity clienteEntity = mapToEntity(cliente);
            return clienteRepository.save(clienteEntity).getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErroAoAcessarRepositorioException("Falha ao executar o metodo" + e);
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            ClienteEntity clienteEntity = mapToEntity(cliente);
            clienteRepository.save(clienteEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErroAoAcessarRepositorioException("Falha ao executar o metodo" + e);
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErroAoAcessarRepositorioException("Falha ao executar o metodo" + e);
        }
    }

    @Override
    public Optional<Cliente> obterPorId(Long id) {
        try {
            Optional<ClienteEntity> clienteEntityOp = clienteRepository.findById(id);

            if (clienteEntityOp.isEmpty()) {
                log.info("Cliente não encontrado: id={}", id);
                return Optional.empty();
            }

            ClienteEntity clienteEntity = clienteEntityOp.get();
            Cliente cliente = mapToDomain(clienteEntity);

            return Optional.of(cliente);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErroAoAcessarRepositorioException("Falha ao executar o metodo" + e);
        }
    }

    private Cliente mapToDomain(ClienteEntity clienteEntity) {
        List<Endereco> enderecos = clienteEntity.getEnderecos().stream()
                .map(this::mapToEnderecoDomain)
                .collect(Collectors.toList());

        return new Cliente(
                clienteEntity.getId(),
                clienteEntity.getNome(),
                clienteEntity.getCpf(),
                clienteEntity.getDataNascimento(),
                enderecos);
    }

    private Endereco mapToEnderecoDomain(EnderecoEntity enderecoEntity) {
        return new Endereco(
                enderecoEntity.getId(),
                enderecoEntity.getRua(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getCep(),
                enderecoEntity.getCidade());
    }

    private ClienteEntity mapToEntity(Cliente cliente) {
        List<EnderecoEntity> enderecos = cliente.getEnderecos().stream()
                .map(this::mapToEnderecoEntity)
                .collect(Collectors.toList());

        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .dataNascimento(cliente.getDataNascimento())
                .enderecos(enderecos)
                .build();
    }

    private EnderecoEntity mapToEnderecoEntity(Endereco endereco) {
        return EnderecoEntity.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .build();
    }
}