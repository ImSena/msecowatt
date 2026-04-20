package br.com.corecode.msecowatt.application.useCases.user.dto;

public record AssignRoleInput(
        String userId,
        String roleName
) {
}
