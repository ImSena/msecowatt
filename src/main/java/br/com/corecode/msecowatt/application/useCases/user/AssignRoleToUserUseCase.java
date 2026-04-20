package br.com.corecode.msecowatt.application.useCases.user;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.user.dto.AssignRoleInput;
import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;
import br.com.corecode.msecowatt.domain.repository.UserRepository;

public class AssignRoleToUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AssignRoleToUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void execute(AssignRoleInput input){
        User user = userRepository.findById(input.userId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Role role = roleRepository.findByName(input.roleName())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        user.addRole(role);
        userRepository.save(user);
    }

}
