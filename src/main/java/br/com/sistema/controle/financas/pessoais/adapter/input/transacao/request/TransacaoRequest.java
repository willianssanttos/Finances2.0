package br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransacaoRequest {

    private Integer idSaldo;
    private Integer idConta;
    private String Descricao;
    private String Categoria;
    private Double Valor;
    private Timestamp dataMovimentacao;
    private int tipo;
}
