package br.com.corecode.msecowatt.application.useCases.company.dto;

public record CreateCompanyInput(
        String name,
        String cnpj,
        String phoneNumber,
        String street,
        String city,
        String state
) {
}
