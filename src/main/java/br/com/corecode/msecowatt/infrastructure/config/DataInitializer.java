package br.com.corecode.msecowatt.infrastructure.config;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;
import br.com.corecode.msecowatt.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            createRoleIfNotExist(roleRepository, "ROLE_ADMIN");
            createRoleIfNotExist(roleRepository, "ROLE_COMMON");
        };
    }

    private void createRoleIfNotExist(RoleRepository roleRepository, String roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    System.out.println("Creating new role: " + roleName);
                    return roleRepository.save(new Role(null, roleName));
                });
    }

}
