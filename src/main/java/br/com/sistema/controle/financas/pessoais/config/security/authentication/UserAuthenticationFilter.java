package br.com.sistema.controle.financas.pessoais.config.security.authentication;

import br.com.sistema.controle.financas.pessoais.config.security.config.SecurityConfiguration;
import br.com.sistema.controle.financas.pessoais.config.security.userdetails.UserDetailsImpl;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.config.exception.TokenException;
import br.com.sistema.controle.financas.pessoais.config.exception.entity.ApiError;
import br.com.sistema.controle.financas.pessoais.port.output.login.ILoginRepository;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ILoginRepository iLoginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = recoveryToken(request);


            if (checkIfEndpointIsNotPublic(request)) {
                if (token != null) {
                    String subject = jwtTokenService.getSubjectFromToken(token);
                    UsuarioEntity login = iLoginRepository.obterLogin(subject);
                    UserDetailsImpl userDetails = new UserDetailsImpl(login);

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new TokenException();
                }
            }
            filterChain.doFilter(request, response);
        } catch (TokenException ex) {
            buidErrorResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name(), ex, response);
        } catch (JWTCreationException ex) {
            buidErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex, response);
        } catch (JWTVerificationException ex) {
            buidErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name(), ex, response);
        }
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_COM_AUTENTICACAO_NAO_OBRIGATORIA).contains(requestURI);
    }

    private void buidErrorResponse(Integer codeError, String statusError, Exception ex, HttpServletResponse response) throws IOException {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(codeError)
                .status(statusError)
                .errors(List.of(ex.getMessage()))
                .build();
        response.setStatus(codeError);
        response.setContentType("application/json");
        response.getWriter().write(convertObjToJson(apiError));
    }

    private String convertObjToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(object);
    }
}
