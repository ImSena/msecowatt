package br.com.corecode.msecowatt.application.useCases.user;

import br.com.corecode.msecowatt.application.exceptions.AlreadyExistsRegisterException;
import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.user.dto.CreateUserInput;
import br.com.corecode.msecowatt.application.useCases.user.dto.UserOutput;
import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;
import br.com.corecode.msecowatt.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutput execute(CreateUserInput input) {
        if(userRepository.existsByUsername(input.username())){
            throw new AlreadyExistsRegisterException("Username already exists");
        }

        Set<Role> roles = input.roles()
                .stream()
                .map(roleName -> roleRepository.findByName(roleName)
                    .orElseThrow(() -> new NotFoundException("Role not found"))
                ).collect(Collectors.toSet());

        String hashedPassword = passwordEncoder.encode(input.password());

        User user = new User(
                null,
                input.username(),
                hashedPassword,
                roles
        );

        User userCreated = userRepository.save(user);

        return UserOutput.from(userCreated);
    }
}
