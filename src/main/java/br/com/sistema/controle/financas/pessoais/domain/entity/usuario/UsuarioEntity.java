package br.com.sistema.controle.financas.pessoais.domain.entity.usuario;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.login.RolesEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class UsuarioEntity extends SaldoEntity implements Serializable {

    private RolesEnum nomeRole;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;
    private List<RolesEntity> roles;

}
