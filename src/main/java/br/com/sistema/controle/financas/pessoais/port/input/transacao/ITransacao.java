package br.com.sistema.controle.financas.pessoais.port.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;

import java.util.List;

public interface ITransacao {
    TransacaoResponse registrarTransacao(TransacaoRequest transacao);
    List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano);
}
