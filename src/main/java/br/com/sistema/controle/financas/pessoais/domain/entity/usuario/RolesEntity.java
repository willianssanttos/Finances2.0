package br.com.sistema.controle.financas.pessoais.domain.entity.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesEntity {

    private Integer idRole;
    private String nomeRole;
}
