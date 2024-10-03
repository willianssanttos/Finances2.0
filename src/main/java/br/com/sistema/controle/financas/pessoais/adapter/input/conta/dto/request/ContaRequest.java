package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.TipoContaEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest extends SaldoEntity {

    private Integer idConta;
    private String nomeConta;
    private Double saldoConta;
    private TipoContaEnum tipoConta;
    private Timestamp dataDeposito;
}
