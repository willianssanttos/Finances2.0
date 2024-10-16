package br.com.sistema.controle.financas.pessoais.port.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ObterContasUsuarioResponse;

public interface IConta {

    ContaResponse criarConta(ContaRequest conta);
    ObterContasUsuarioResponse obterContasUsuario(String token, Integer idUsario);
    void editarConta(ContaRequest conta);
    void excluirConta(Integer idConta);
}
