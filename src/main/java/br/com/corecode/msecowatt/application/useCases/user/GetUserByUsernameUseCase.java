package br.com.corecode.msecowatt.application.useCases.user;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.user.dto.UserOutput;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.UserRepository;

public class GetUserByUsernameUseCase {

    private final UserRepository userRepository;

    public GetUserByUsernameUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));

        return UserOutput.from(user);
    }
}
