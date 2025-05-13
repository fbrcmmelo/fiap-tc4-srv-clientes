package br.com.fiap.fiaptc4srvclientes.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos;

    public boolean possuiEndereco() {
        return enderecos != null && !enderecos.isEmpty();
    }
}
