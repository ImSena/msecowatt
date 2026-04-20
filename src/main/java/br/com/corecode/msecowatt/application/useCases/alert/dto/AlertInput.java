package br.com.corecode.msecowatt.application.useCases.alert.dto;

public record AlertInput(
        String companyId,
        String type,
        String message
) {
}
