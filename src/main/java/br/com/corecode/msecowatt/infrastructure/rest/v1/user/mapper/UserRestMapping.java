package br.com.corecode.msecowatt.infrastructure.rest.v1.user.mapper;

import br.com.corecode.msecowatt.application.useCases.user.dto.CreateUserInput;
import br.com.corecode.msecowatt.application.useCases.user.dto.UserOutput;
import br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto.UserRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.user.dto.UserResponse;

public class UserRestMapping {

    public static CreateUserInput toInput(UserRequest request) {
        return new CreateUserInput(
                request.username(), request.password(), request.roles()
        );
    }

    public static UserResponse toResponse(UserOutput userOutput) {
        return new UserResponse(
                userOutput.id(),
                userOutput.username(),
                userOutput.roles()
        );
    }
}
