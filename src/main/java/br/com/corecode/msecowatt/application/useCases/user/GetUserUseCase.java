package br.com.corecode.msecowatt.application.useCases.user;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.user.dto.UserOutput;
import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public class GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(String id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("User not found")
        );

        return UserOutput.from(user);
    }
}
