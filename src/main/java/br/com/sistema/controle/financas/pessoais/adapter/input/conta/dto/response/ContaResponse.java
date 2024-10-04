package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.TipoContaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContaResponse {

    private String nomeConta;
    private Double saldoConta;
    private TipoContaEnum tipoConta;
    private Timestamp dataDeposito;

}
