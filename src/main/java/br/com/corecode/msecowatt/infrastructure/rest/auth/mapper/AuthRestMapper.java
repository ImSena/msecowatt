package br.com.corecode.msecowatt.infrastructure.rest.auth.mapper;

import br.com.corecode.msecowatt.application.useCases.auth.dto.AuthInput;
import br.com.corecode.msecowatt.application.useCases.auth.dto.AuthOutput;
import br.com.corecode.msecowatt.infrastructure.rest.auth.dto.AuthRequest;
import br.com.corecode.msecowatt.infrastructure.rest.auth.dto.AuthResponse;

public class AuthRestMapper {

    public static AuthInput toInput(AuthRequest authRequest) {
        return new AuthInput(authRequest.username(), authRequest.password());
    }

    public static AuthResponse toResponse(AuthOutput authOutput) {
        return new AuthResponse(authOutput.accessToken(), authOutput.expiresIn());
    }
}
