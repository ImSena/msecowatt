package br.com.corecode.msecowatt.application.useCases.auth.dto;

public record AuthInput(
        String username,
        String password
) {
}
