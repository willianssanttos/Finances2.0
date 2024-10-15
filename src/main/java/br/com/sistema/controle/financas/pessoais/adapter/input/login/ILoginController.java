package br.com.sistema.controle.financas.pessoais.adapter.input.login;

import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.JwtTokenResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILoginController {

    @Operation(description = "Operação para logar usuario na plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!"),
            @ApiResponse(responseCode = "417", description = "Erro token invalidado ou expirado!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao realizar login!")
    })
    ResponseEntity<JwtTokenResponse> autenticarUsuario(@RequestBody LoginResponse usuarioLogin);
}
