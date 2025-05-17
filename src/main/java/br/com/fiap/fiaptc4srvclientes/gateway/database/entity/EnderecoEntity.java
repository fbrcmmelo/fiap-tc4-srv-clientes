package br.com.fiap.fiaptc4srvclientes.gateway.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;

    @OneToOne(mappedBy = "endereco")
    private ClienteEntity cliente;
}
