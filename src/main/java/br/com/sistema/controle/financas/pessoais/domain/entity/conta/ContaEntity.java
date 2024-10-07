package br.com.sistema.controle.financas.pessoais.domain.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ContaEntity extends SaldoEntity{

    private Integer idConta;
    private String nomeConta;
    private Double saldoConta;
    private String tipoConta;
    private Timestamp dataDeposito;

}


