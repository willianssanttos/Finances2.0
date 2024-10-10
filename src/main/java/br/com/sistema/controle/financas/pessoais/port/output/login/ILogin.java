package br.com.sistema.controle.financas.pessoais.port.output.login;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.RolesEntity;

public interface ILogin {

    RolesEntity obterRole(String nomeRole);
}
