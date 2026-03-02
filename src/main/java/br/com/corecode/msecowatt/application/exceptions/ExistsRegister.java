package br.com.corecode.msecowatt.application.exceptions;

public class ExistsRegister extends RuntimeException {
    public ExistsRegister(String message) {
        super(message);
    }
}
