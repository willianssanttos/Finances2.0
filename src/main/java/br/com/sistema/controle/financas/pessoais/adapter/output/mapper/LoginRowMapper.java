package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRowMapper implements RowMapper<UsuarioEntity> {

    public UsuarioEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioEntity login = new UsuarioEntity();
        login.setIdUsuario(rs.getInt(1));
        login.setEmailUsuario(rs.getString(2));
        login.setSenhaUsuario(rs.getString(3));
        login.setNomeRole(RolesEnum.valueOf(rs.getString(4)));
        return login;
    }
}
