package br.com.sistema.controle.financas.pessoais.domain.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TipoContaEntity {

    private Integer idTipoConta;
    private String nomeTipoConta;

}
