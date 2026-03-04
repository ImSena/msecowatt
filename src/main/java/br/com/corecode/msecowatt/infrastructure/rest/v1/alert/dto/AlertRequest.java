package br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto;

import jakarta.validation.constraints.NotBlank;

public record AlertRequest(
        @NotBlank String companyId,
        @NotBlank String type,
        @NotBlank String message
) {
}
