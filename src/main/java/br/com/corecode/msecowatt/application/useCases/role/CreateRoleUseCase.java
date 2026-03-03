package br.com.corecode.msecowatt.application.useCases.role;

import br.com.corecode.msecowatt.application.exceptions.AlreadyExistsRegisterException;
import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;

public class CreateRoleUseCase {

    private final RoleRepository repository;

    public CreateRoleUseCase(RoleRepository repository) {
        this.repository = repository;
    }

    public void execute(String name){
        repository.findById(name)
                .ifPresent(role -> {
                    throw new AlreadyExistsRegisterException("Role not found");
                });
        Role role = new Role(null, name);
        repository.save(role);
    }

}
