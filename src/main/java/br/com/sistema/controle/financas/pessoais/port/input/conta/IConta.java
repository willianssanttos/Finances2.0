package br.com.sistema.controle.financas.pessoais.port.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;

public interface IConta {

    ContaResponse criarConta(ContaRequest conta);
    void editarConta(ContaRequest conta);
}
