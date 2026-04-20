package br.com.corecode.msecowatt.infrastructure.rest.auth.dto;

import java.util.Set;

public record AuthResponse(
        String accessToken,
        Long expiresIn
) {
}
