package br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto;

import java.util.Set;

public record UserRequest(
        String username,
        String password,
        Set<String> roles
) {
}
