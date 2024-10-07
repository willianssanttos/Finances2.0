package br.com.sistema.controle.financas.pessoais.domain.entity.transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransacoesContaEntity {

    private Integer idTransacao;
    private Integer idSaldo;
    private Integer idConta;
    private String Descricao;
    private String Categoria;
    private Double Valor;
    private Timestamp dataMovimentacao;
    private int tipo;
}
