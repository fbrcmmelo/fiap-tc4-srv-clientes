package br.com.fiap.fiaptc4srvclientes.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoJson {
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
}
