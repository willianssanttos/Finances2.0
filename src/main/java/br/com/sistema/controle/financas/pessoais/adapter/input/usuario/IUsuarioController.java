package br.com.sistema.controle.financas.pessoais.adapter.input.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUsuarioController {

    @Operation(description = "Operação para cadastrar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!"),
            @ApiResponse(responseCode = "417", description = "Erro ao realizar o cadastro!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao realizar cadastro!")
    })
    ResponseEntity criaUsuario(@RequestBody UsuarioRequest usuario);
}
