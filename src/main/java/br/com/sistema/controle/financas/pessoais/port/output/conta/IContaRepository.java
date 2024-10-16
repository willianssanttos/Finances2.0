package br.com.sistema.controle.financas.pessoais.port.output.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;

import java.util.List;

public interface IContaRepository {

    ContaEntity criarConta(ContaEntity conta);
    void editarConta(ContaRequest conta);
    List<ContaEntity> obterContasPorUsuario(Integer idUsuario);
     void excluirConta(Integer idConta);

}
