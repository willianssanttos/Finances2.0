package br.com.sistema.controle.financas.pessoais.adapter.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.ExtratoTransacaoResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITransacaoController {

    @Operation(description = "Operação para registrar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!"),
            @ApiResponse(responseCode = "417", description = "Erro ao realizar o cadastro!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao realizar cadastro!")
    })
    ResponseEntity<TransacaoResponse> realizarTransacao(@RequestBody TransacaoRequest transacao);

    @Operation(description = "Operação para listagem de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações recuperada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao listar transações!")
    })
    ResponseEntity<ExtratoTransacaoResponse> extratoTransacao(Integer idUsuario, Integer mes, Integer ano);
}
