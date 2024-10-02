package br.com.sistema.controle.financas.pessoais.port.input.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;

public interface IUsuario {

    UsuarioResponse criarUsuario(UsuarioRequest usuario);
}
