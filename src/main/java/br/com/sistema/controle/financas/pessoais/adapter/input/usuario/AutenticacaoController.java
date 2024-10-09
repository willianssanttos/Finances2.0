package br.com.sistema.controle.financas.pessoais.adapter.input.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.AuthenticationDTO;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.JwtResponse;
import br.com.sistema.controle.financas.pessoais.domain.command.security.UserDetailsImpl;
import br.com.sistema.controle.financas.pessoais.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
@CrossOrigin
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/user")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
