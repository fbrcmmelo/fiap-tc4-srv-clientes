package br.com.fiap.fiaptc4srvclientes.gateway.database.jpa;


import java.util.Optional;

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
        return new Cliente(
                clienteEntity.getId(),
                clienteEntity.getNome(),
                clienteEntity.getCpf(),
                clienteEntity.getDataNascimento(),
                new Endereco(
                        clienteEntity.getEndereco().getRua(),
                        clienteEntity.getEndereco().getNumero(),
                        clienteEntity.getEndereco().getComplemento(),
                        clienteEntity.getEndereco().getCep(),
                        clienteEntity.getEndereco().getCidade()
                )
        );
    }

    private Endereco mapToEnderecoDomain(EnderecoEntity enderecoEntity) {
        return new Endereco(
                enderecoEntity.getRua(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getCep(),
                enderecoEntity.getCidade()
        );
    }

    private ClienteEntity mapToEntity(Cliente cliente) {

        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .dataNascimento(cliente.getDataNascimento())
                .endereco(new EnderecoEntity(
                        null,
                        cliente.getEndereco().getRua(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento(),
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getCidade(),
                        null
                )).build();

    }

    private EnderecoEntity mapToEnderecoEntity(Endereco endereco) {
        return EnderecoEntity.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .build();
    }
}