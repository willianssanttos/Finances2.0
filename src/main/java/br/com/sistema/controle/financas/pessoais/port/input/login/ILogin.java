package br.com.sistema.controle.financas.pessoais.port.input.login;

import br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response.JwtTokenResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;

public interface ILogin {

    JwtTokenResponse authenticateUser(UsuarioResponse loginUserDto);
}
