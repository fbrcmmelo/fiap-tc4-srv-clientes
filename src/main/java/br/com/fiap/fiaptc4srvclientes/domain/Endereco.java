package br.com.fiap.fiaptc4srvclientes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Endereco {
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
}
