package br.com.fiap.fiaptc4srvclientes.exception;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException() {
        super("Já existe um cliente cadastrado com este CPF");
    }
}