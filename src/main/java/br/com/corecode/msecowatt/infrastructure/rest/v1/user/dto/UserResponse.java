package br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto;

import java.util.Set;

public record UserResponse(
        String id,
        String username,
        Set<String> roles
) {
}
