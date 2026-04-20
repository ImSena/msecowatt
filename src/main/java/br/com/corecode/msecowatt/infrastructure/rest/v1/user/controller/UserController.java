package br.com.corecode.msecowatt.infrastructure.rest.v1.user.controller;

import br.com.corecode.msecowatt.application.useCases.user.CreateUserUseCase;
import br.com.corecode.msecowatt.infrastructure.annotations.ApiV1;
import br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto.UserRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto.UserResponse;
import br.com.corecode.msecowatt.infrastructure.rest.v1.user.mapper.UserRestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiV1
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        var input = UserRestMapping.toInput(request);
        var output = UserRestMapping.toResponse(createUserUseCase.execute(input));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(output);

    }

}
