package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObterContasUsuarioResponse {

    private Double saldo;
    private List<ContaEntity> contas;
}
