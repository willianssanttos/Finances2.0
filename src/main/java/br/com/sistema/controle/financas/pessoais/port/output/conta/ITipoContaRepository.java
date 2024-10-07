package br.com.sistema.controle.financas.pessoais.port.output.conta;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;

import java.util.List;

public interface ITipoContaRepository {

     TipoContaEntity obterTiposConta(String nomeTipoConta);
}
