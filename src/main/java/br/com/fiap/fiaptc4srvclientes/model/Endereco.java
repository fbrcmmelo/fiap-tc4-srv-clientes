package br.com.fiap.fiaptc4srvclientes.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
}
