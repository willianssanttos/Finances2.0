package br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransacaoRequest {

    private Integer idConta;
    private Integer idSaldo;
    private String descricao;
    private String categoria;
    private Double valor;
    private Timestamp dataMovimentacao;
    private int tipo;
}
