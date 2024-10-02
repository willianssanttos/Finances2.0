package br.com.sistema.controle.financas.pessoais.port.input.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;

public interface IUsuario {

    UsuarioEntity criarUsuario(UsuarioEntity usuario);
}
