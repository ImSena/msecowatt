package br.com.corecode.msecowatt.application.useCases.user.dto;

import java.util.Set;

public record CreateUserInput(
    String username,
    String password,
    Set<String> roles
) {}
