package br.com.corecode.msecowatt.application.useCases.auth;

import br.com.corecode.msecowatt.application.useCases.auth.dto.AuthInput;
import br.com.corecode.msecowatt.application.useCases.auth.dto.AuthOutput;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;

public class AuthenticateUserUseCase {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEnconder;
    @Value("${jwt.expiresin}")
    private Long expiresIn;

    public AuthenticateUserUseCase(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEnconder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEnconder = jwtEnconder;
    }

    public AuthOutput execute(AuthInput input) {
        User user = userRepository.findByUsername(input.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if(!bCryptPasswordEncoder.matches(input.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://imsena.corecode.com.br")
                .subject(user.getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        String accessToken = jwtEnconder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new AuthOutput(accessToken, expiresIn);
    }
}
