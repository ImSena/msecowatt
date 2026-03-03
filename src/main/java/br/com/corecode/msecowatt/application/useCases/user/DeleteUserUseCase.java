package br.com.corecode.msecowatt.application.useCases.user;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.domain.repository.UserRepository;

public class DeleteUserUseCase {

    private final UserRepository repository;

    public DeleteUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String id){
        repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        repository.deleteById(id);
    }
}
