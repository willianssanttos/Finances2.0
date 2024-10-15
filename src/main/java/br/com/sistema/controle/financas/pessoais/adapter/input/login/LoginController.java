package br.com.sistema.controle.financas.pessoais.adapter.input.login;

import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.JwtTokenResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.LoginResponse;
import br.com.sistema.controle.financas.pessoais.port.input.login.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
public class LoginController implements ILoginController {

    @Autowired
    private ILogin iLogin;

    @PostMapping("/usuario")
    public ResponseEntity<JwtTokenResponse> autenticarUsuario(@RequestBody LoginResponse usuarioLogin) {
        JwtTokenResponse token = iLogin.authenticateUser(usuarioLogin);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
