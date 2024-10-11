package br.com.sistema.controle.financas.pessoais.adapter.input.auth;

import br.com.sistema.controle.financas.pessoais.adapter.input.auth.dto.response.JwtTokenResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.port.input.login.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private ILogin iLogin;

    @PostMapping("/usuario")
    public ResponseEntity<JwtTokenResponse> autenticarUsuario(@RequestBody UsuarioResponse authDto) {
        JwtTokenResponse token = iLogin.authenticateUser(authDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
