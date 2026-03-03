package br.com.corecode.msecowatt.application.exceptions;

public class AlreadyExistsRegisterException extends RuntimeException {
    public AlreadyExistsRegisterException(String message) {
        super(message);
    }
}
