package br.com.corecode.msecowatt.application.useCases.alert.dto;

public record AlertOutput(
        String id,
        String companyId,
        String type,
        String message,
        Boolean resolved
) {
}
