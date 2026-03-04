package br.com.corecode.msecowatt.infrastructure.rest.auth.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record AuthRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
