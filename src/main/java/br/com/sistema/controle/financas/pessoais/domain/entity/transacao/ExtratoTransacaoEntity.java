package br.com.sistema.controle.financas.pessoais.domain.entity.transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoTransacaoEntity {

    private String nomeConta;
    private String tipoConta;
    private String descricao;
    private String categoria;
    private Double valor;
    private Timestamp dataMovimentacao;
    private String tipoTransacao;
    
}
