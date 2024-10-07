package br.com.sistema.controle.financas.pessoais.port.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;

import java.util.List;

public interface ITransacao {
    TransacaoResponse registrarTransacao(TransacaoRequest transacao);
    List<ExtratoTransacaoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano);
}
