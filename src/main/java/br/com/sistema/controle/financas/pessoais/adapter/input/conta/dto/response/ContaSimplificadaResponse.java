package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaSimplificadaResponse {

    private Integer idConta;
    private String nomeConta;
    private Double saldoConta;
    private String tipoConta;
    private Timestamp dataDeposito;

    // Construtor para criar a partir de ContaEntity
    public ContaSimplificadaResponse(ContaEntity conta) {
        this.idConta = conta.getIdConta();
        this.nomeConta = conta.getNomeConta();
        this.saldoConta = conta.getSaldoConta();
        this.tipoConta = conta.getTipoConta();
        this.dataDeposito = conta.getDataDeposito();
    }
}
