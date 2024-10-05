package br.com.sistema.controle.financas.pessoais.port.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;

public interface ITransacao {
    TransacaoResponse registrarTransacao(TransacaoRequest transacao);
}
