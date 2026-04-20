package br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto;

public record AlertResponse(
        String id,
        String companyId,
        String type,
        String message,
        Boolean resolved
) {
}
