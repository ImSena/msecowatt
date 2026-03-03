package br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequest(
        @NotBlank String name,
        @NotBlank String cnpj,
        @NotBlank String phoneNumber,
        @NotBlank String street,
        @NotBlank String city,
        @NotBlank String state
) {
}
