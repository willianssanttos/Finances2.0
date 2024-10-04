package br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransacaoResponse {

    private String Descricao;
    private String Categoria;
    private Double Valor;
    private Timestamp dataMovimentacao;
    private int tipo;
}
