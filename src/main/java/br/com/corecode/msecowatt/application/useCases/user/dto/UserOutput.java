package br.com.corecode.msecowatt.application.useCases.user.dto;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserOutput(
        String id,
        String username,
        Set<String> roles
) {
    public static UserOutput from(User user) {
        return new UserOutput(
                user.getId(),
                user.getUsername(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}
