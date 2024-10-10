package br.com.sistema.controle.financas.pessoais.security.jwt;

import br.com.sistema.controle.financas.pessoais.domain.command.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${spring.application.projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.application.projeto.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException("Token inválido: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Token expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("Token não suportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Token Argumento inválido: " + e.getMessage());
        }
    }

    private Key getSigninKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
