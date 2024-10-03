package br.com.sistema.controle.financas.pessoais.port.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;

public interface IUsuarioRepository {

    UsuarioEntity criarUsuario(UsuarioEntity usuario);
    boolean verificarEmailExistente(String email);

}
