package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SaldoRequest {

    private Integer idSaldo;
    private Integer idUsuario;
    private Double saldoAtual;
    private Timestamp dataAtualizadaSaldo;
}
