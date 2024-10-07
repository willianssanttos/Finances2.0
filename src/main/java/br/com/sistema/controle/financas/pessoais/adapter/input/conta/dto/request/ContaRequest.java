package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.TipoContaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ContaRequest extends SaldoRequest {

    private String nomeConta;
    private Double saldoConta;
    private TipoContaEnum tipoConta;
    private Timestamp dataDeposito;
}
