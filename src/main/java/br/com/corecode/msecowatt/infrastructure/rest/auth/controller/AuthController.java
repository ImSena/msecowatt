package br.com.corecode.msecowatt.infrastructure.rest.auth.controller;

import br.com.corecode.msecowatt.application.useCases.auth.AuthenticateUserUseCase;
import br.com.corecode.msecowatt.infrastructure.rest.auth.dto.AuthRequest;
import br.com.corecode.msecowatt.infrastructure.rest.auth.dto.AuthResponse;
import br.com.corecode.msecowatt.infrastructure.rest.auth.mapper.AuthRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        var input = AuthRestMapper.toInput(request);
        var output = AuthRestMapper.toResponse(
                authenticateUserUseCase.execute(input)
        );

        return ResponseEntity.ok(output);
    }

}
