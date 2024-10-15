package br.com.sistema.controle.financas.pessoais.config.security.authentication;

import br.com.sistema.controle.financas.pessoais.config.security.userdetails.UserDetailsImpl;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Service
public class JwtTokenService {

    @Value("${spring.application.projeto.jwtSecret}")
    private String jwtSecret;

    private static final String EMISSOR = "Finances2.0";

    public String generateJwtToken(UserDetailsImpl login) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            String token = JWT.create()
                    .withIssuer(EMISSOR)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(4, ChronoUnit.HOURS))
                    .withSubject(login.getUsername())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new JWTCreationException(Constantes.GerarToken, exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .withIssuer(EMISSOR)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException(Constantes.Token);
        }
    }

    private Instant creationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
