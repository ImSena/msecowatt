package br.com.corecode.msecowatt.application.useCases.role;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;

import java.util.List;

public class ListRolesUseCase {
    private final RoleRepository repository;

    public ListRolesUseCase(RoleRepository repository) {
        this.repository = repository;
    }

    public List<String> execute() {
        return repository.findAll()
                .stream()
                .map(Role::getName)
                .toList();
    }
}
