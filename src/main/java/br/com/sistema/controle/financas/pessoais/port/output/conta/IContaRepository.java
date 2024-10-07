package br.com.sistema.controle.financas.pessoais.port.output.conta;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;

public interface IContaRepository {

    ContaEntity criarConta(ContaEntity conta);

//    List<ContaEntity> obterContasPorUsuario(Integer idUsuario);
//
//    void excluirConta(Integer idConta);
//    void editarConta(ContaEntity conta);
}
