package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.command.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.login.RolesEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesRowMapper implements RowMapper<RolesEntity> {

    public RolesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        RolesEntity role = new RolesEntity();
        role.setIdRole(rs.getInt(1));
        role.setNomeRole(RolesEnum.valueOf(rs.getString(2)));
        return role;
    }
}
