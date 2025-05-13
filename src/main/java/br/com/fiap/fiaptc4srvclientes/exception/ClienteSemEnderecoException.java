package br.com.fiap.fiaptc4srvclientes.exception;

public class ClienteSemEnderecoException extends RuntimeException {
    public ClienteSemEnderecoException() {
        super("O cliente deve ter pelo menos um endereço cadastrado");
    }
}
