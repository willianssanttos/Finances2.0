package br.com.sistema.controle.financas.pessoais.adapter.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ObterContasUsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IContaController {

    @Operation(description = "Operação para cadastrar conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!"),
            @ApiResponse(responseCode = "417", description = "Erro ao realizar o cadastro!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao realizar cadastro!")
    })
    ResponseEntity<ContaResponse> criaConta(@RequestBody ContaRequest conta);

    @Operation(description = "Operação para atualizar conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar conta!"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar conta!")
    })
    ResponseEntity<Void> atualizarConta(@PathVariable Integer idConta, @RequestBody ContaRequest conta);
    @Operation(description = "Operação para listagem de contas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista das contas recuperada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao listar contas!")
    })
    ResponseEntity<ObterContasUsuarioResponse> obterContas(@PathVariable Integer idUsuario);

    @Operation(description = "Operação para deletar conta ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta excluída com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao excluir conta!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao excluir conta!")
    })
    ResponseEntity<Void> deletarConta(@PathVariable Integer idConta);

}
