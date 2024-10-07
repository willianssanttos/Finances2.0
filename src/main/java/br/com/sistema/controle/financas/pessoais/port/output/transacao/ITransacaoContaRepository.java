package br.com.sistema.controle.financas.pessoais.port.output.transacao;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.TransacoesContaEntity;

import java.util.List;

public interface ITransacaoContaRepository {

    TransacoesContaEntity inserirTransacao(TransacoesContaEntity transacao);
    List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano);
}
