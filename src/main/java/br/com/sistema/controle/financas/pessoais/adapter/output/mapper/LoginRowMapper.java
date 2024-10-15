package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.login.RolesEntity;
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
        // Cria um objeto RolesEntity e adiciona na lista nomeRole
        RolesEntity role = new RolesEntity();
        role.setNomeRole(RolesEnum.valueOf(rs.getString(4)));
        login.getNomeRole().add(role);
        return login;
    }
}
