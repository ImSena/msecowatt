package br.com.corecode.msecowatt.application.exceptions;

public class StartDateAfterException extends RuntimeException {
    public StartDateAfterException(String message) {
        super(message);
    }
}
