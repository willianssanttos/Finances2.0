package br.com.sistema.controle.financas.pessoais.domain.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtratoEntity {

    private String nomeConta;
    private String descricao;
    private String categoria;
    private Double valor;
    private Timestamp dataMovimentacao;
    private String tipoTransacao;
    private String tipoConta;

}
