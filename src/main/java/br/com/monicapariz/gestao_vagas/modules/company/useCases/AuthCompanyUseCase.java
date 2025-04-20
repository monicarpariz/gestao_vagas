package br.com.monicapariz.gestao_vagas.modules.company.useCases;

import br.com.monicapariz.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.monicapariz.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.monicapariz.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String jwtSecret;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(
                        () -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(
                authCompanyDTO.getPassword(),
                company.getPassword()
        );

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        var ExpiresAt = Instant.now()
                .plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(ExpiresAt)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .accessToken(token)
                .expires_at(ExpiresAt.toEpochMilli())
                .build();

        return authCompanyResponseDTO;

    }

}
