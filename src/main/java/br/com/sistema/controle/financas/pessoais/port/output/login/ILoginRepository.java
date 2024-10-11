package br.com.sistema.controle.financas.pessoais.port.output.login;

import br.com.sistema.controle.financas.pessoais.domain.entity.login.RolesEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;


public interface ILoginRepository {

    RolesEntity obterRole(String nomeRole);

    UsuarioEntity obterLogin(String email);
}
