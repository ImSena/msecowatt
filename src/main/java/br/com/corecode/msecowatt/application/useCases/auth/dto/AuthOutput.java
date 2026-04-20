package br.com.corecode.msecowatt.application.useCases.auth.dto;

public record AuthOutput(String accessToken, Long expiresIn) {
}
