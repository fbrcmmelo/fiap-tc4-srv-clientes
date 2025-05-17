package br.com.fiap.fiaptc4srvclientes.controller;

import br.com.fiap.fiaptc4srvclientes.controller.json.ClienteJson;
import br.com.fiap.fiaptc4srvclientes.controller.json.EnderecoJson;
import br.com.fiap.fiaptc4srvclientes.domain.Cliente;
import br.com.fiap.fiaptc4srvclientes.domain.Endereco;
import br.com.fiap.fiaptc4srvclientes.usecase.AtualizarClienteUsecase;
import br.com.fiap.fiaptc4srvclientes.usecase.CriarClienteUseCase;
import br.com.fiap.fiaptc4srvclientes.usecase.ExcluirClienteUsecase;
import br.com.fiap.fiaptc4srvclientes.usecase.ObterClienteUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CriarClienteUseCase criarClienteUsecase;
    private final AtualizarClienteUsecase atualizarClienteUsecase;
    private final ExcluirClienteUsecase excluirClienteUsecase;
    private final ObterClienteUsecase obterClienteUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> criar(@Valid @RequestBody ClienteJson clienteJson) {
        log.info("Recebendo requisição para criar cliente: {}", clienteJson);
        Long id = criarClienteUsecase.criar(mapToDomain(clienteJson));
        log.info("Cliente criado com ID: {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteJson clienteJson) {
        log.info("Recebendo requisição para atualizar cliente ID {}: {}", id, clienteJson);
        Cliente cliente = mapToDomain(clienteJson);
        atualizarClienteUsecase.atualizar(cliente);
        log.info("Cliente ID {} atualizado com sucesso", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        log.info("Recebendo requisição para excluir cliente ID: {}", id);
        excluirClienteUsecase.excluir(id);
        log.info("Cliente ID {} excluído com sucesso", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteJson> obterPorId(@PathVariable Long id) {
        log.info("Recebendo requisição para obter cliente ID: {}", id);
        ClienteJson response = mapToJson(obterClienteUsecase.obterPorId(id));
        log.info("Retornando cliente ID {}: {}", id, response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteJson> obterPorCpf(@PathVariable String cpf) {
        log.info("Recebendo requisição para obter cliente por CPF: {}", cpf);
        ClienteJson response = mapToJson(obterClienteUsecase.obterPorCpf(cpf));
        log.info("Retornando cliente CPF {}: {}", cpf, response);
        return ResponseEntity.ok(response);
    }

    private Cliente mapToDomain(ClienteJson clienteJson) {
        return new Cliente(
                null,
                clienteJson.getNome(),
                clienteJson.getCpf(),
                clienteJson.getDataNascimento(),
                mapToDomain(clienteJson.getEndereco())
        );
    }

    private List<Endereco> mapToDomain(List<EnderecoJson> enderecosJson) {
        return enderecosJson.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private Endereco mapToDomain(EnderecoJson enderecoJson) {
        return new Endereco(
                enderecoJson.getRua(),
                enderecoJson.getNumero(),
                enderecoJson.getComplemento(),
                enderecoJson.getCep(),
                enderecoJson.getCidade()
        );
    }

    private ClienteJson mapToJson(Cliente cliente) {
        return ClienteJson.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .dataNascimento(cliente.getDataNascimento())
                .endereco(mapToJson(cliente.getEndereco()))
                .build();
    }

    private List<EnderecoJson> mapToJson(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(this::mapToJson)
                .collect(Collectors.toList());
    }

    private EnderecoJson mapToJson(Endereco endereco) {
        return EnderecoJson.builder()
                .rua(endereco.getRua())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .build();
    }
}