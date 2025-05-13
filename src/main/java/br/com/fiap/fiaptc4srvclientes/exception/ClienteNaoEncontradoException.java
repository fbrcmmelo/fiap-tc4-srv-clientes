package br.com.fiap.fiaptc4srvclientes.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado");
    }
}