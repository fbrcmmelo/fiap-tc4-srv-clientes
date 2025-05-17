package br.com.fiap.fiaptc4srvclientes.controller.json;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera getters, setters, equals, hashCode e toString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteJson {
    private String nome;
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private EnderecoJson endereco;
}
