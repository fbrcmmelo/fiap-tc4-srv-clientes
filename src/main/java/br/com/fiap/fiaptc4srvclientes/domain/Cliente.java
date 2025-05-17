package br.com.fiap.fiaptc4srvclientes.domain;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode estar vazio");
        }

        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }

        if (endereco == null) {
            throw new IllegalArgumentException("Endereco não pode estar vazio");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public boolean possuiEndereco() {
        return endereco != null;
    }

}

