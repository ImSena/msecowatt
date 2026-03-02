package br.com.corecode.msecowatt.application.exceptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
