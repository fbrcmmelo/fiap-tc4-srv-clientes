package br.com.fiap.fiaptc4srvclientes.controller;

import br.com.fiap.fiaptc4srvclientes.usecase.AtualizarClienteUsecase;
import br.com.fiap.fiaptc4srvclientes.usecase.CriarClienteUseCase;
import br.com.fiap.fiaptc4srvclientes.usecase.ExcluirClienteUsecase;
import br.com.fiap.fiaptc4srvclientes.usecase.ObterClienteUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;



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
    public Long criar(@Valid @RequestBody ClienteJson clienteJson) {
        return criarClienteUsecase.criar(mapToDomain(clienteJson));
    }

    @PutMapping
    public void atualizar(@Valid @RequestBody ClienteJson clienteJson) {
        atualizarClienteUsecase.atualizar(mapToDomain(clienteJson));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        excluirClienteUsecase.excluir(id);
    }

    @GetMapping("/{id}")
    public ClienteJson obterPorId(@PathVariable Long id) {
        return mapToJson(obterClienteUsecase.obterPorId(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ClienteJson obterPorCpf(@PathVariable String cpf) {
        return mapToJson(obterClienteUsecase.obterPorCpf(cpf));
    }

    private Cliente mapToDomain(ClienteJson clienteJson) {
        // Implementação similar ao exemplo do Usuario
        // Mapear também os endereços
        return new Cliente(
                clienteJson.getId(),
                clienteJson.getNome(),
                clienteJson.getCpf(),
                clienteJson.getDataNascimento(),
                // Mapear endereços
        );
    }

    private ClienteJson mapToJson(Cliente cliente) {
        // Implementação de conversão para JSON
        return new ClienteJson(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNascimento(),
                // Mapear endereços
        );
    }
}

