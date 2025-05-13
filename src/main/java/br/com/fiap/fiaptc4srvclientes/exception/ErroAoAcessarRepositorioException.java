package br.com.fiap.fiaptc4srvclientes.exception;

public class ErroAoAcessarRepositorioException extends RuntimeException {
    public ErroAoAcessarRepositorioException(String mensagem) {
        super("Erro ao acessar repositorio: " + mensagem);
    }
}
