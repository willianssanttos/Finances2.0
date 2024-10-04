package br.com.sistema.controle.financas.pessoais.port.output.conta;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;

public interface ISaldoRepository {

    SaldoEntity inserirSaldo(SaldoEntity saldo);

}
