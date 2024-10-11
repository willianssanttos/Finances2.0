package br.com.sistema.controle.financas.pessoais.domain.entity.login;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LoginEntity extends UsuarioEntity {

    private List<RolesEntity> roles;
}
