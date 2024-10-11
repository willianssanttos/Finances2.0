package br.com.sistema.controle.financas.pessoais.domain.entity.login;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.RolesEnum;
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
    private RolesEnum nomeRole;
}
