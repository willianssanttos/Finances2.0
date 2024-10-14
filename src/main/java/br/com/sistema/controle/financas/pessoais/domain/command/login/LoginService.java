package br.com.sistema.controle.financas.pessoais.domain.command.login;

import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.JwtTokenResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.domain.command.security.authentication.JwtTokenService;
import br.com.sistema.controle.financas.pessoais.domain.command.security.userdetails.UserDetailsImpl;
import br.com.sistema.controle.financas.pessoais.port.input.login.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILogin {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    public JwtTokenResponse authenticateUser(UsuarioResponse loginUserDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmailUsuario(), loginUserDto.getSenhaUsuario());


        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        return new JwtTokenResponse(jwtTokenService.generateJwtToken(userDetails));
    }
}
