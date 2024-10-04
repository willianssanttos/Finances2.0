package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.TipoContaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaResponse {

    private Integer idConta;
    private String nomeConta;
    private Double saldoConta;
    private TipoContaEnum tipoConta;
    private Timestamp dataDeposito;

    public ContaResponse(Integer idConta, String tipoConta, String nomeConta, Double saldoConta, Timestamp dataDeposito) {
    }
}
