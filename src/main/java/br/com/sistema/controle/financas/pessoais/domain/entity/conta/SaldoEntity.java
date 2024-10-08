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
public class SaldoEntity {

    private Integer idSaldo;
    private Integer idUsuario;
    private Double saldoAtual;
    private Timestamp dataAtualizadaSaldo;

}
